package com.picsart;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class SalesProcessing {

    /**
     * Processes a new sale by updating stock quantity, calculating total price, and inserting a sale record
     * into the database.
     *
     * @param customer The Customer object representing the customer making the purchase.
     * @param book     The Book object representing the book being purchased.
     * @param sqlDate  The date of the sale.
     * @param quantity The quantity of books being purchased.
     */
     public  void processNewSale(Customer customer, Book book, Date sqlDate, int quantity) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()){

            transaction = session.beginTransaction();

            if (isStockAvailable(session, book.getBookID(), quantity)) {
                double totalPrice = calculateTotalPrice(session, book.getBookID(), quantity);
                updateStockQuantity(session, book.getBookID(), quantity);
                Sale sale = new Sale(book, customer, sqlDate, quantity, totalPrice);
                insertSaleRecord(session, sale);

                transaction.commit();

                System.out.println("Sale processed successfully. Total Price: $" + totalPrice);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if there is sufficient stock available for a given book and quantity.
     *
     * @param session  The Hibernate Session object.
     * @param bookID   The ID of the book.
     * @param quantity The quantity being checked.
     * @return True if stock is available, false otherwise.
     */
    private  boolean isStockAvailable(Session session, int bookID, int quantity) {
        Book book = session.get(Book.class, bookID);
        return book != null && book.getQuantityInStock() >= quantity;
    }

    /**
     * Calculates the total price for a given book and quantity.
     *
     * @param session  The Hibernate Session object.
     * @param bookID   The ID of the book.
     * @param quantity The quantity for which total price is calculated.
     * @return The total price.
     */
    private  double calculateTotalPrice(Session session, int bookID, int quantity) {
        Book book = session.get(Book.class, bookID);
        return (book != null) ? quantity * book.getPrice() : 0;
    }

    /**
     * Updates the stock quantity of a given book after a sale.
     *
     * @param session  The Hibernate Session object.
     * @param bookID   The ID of the book.
     * @param quantity The quantity to be deducted from stock.
     */
    private  void updateStockQuantity(Session session, int bookID, int quantity) {
        Book book = session.get(Book.class, bookID);
        if (book != null) {
            book.setQuantityInStock(book.getQuantityInStock() - quantity);
            session.merge(book);
        }
    }

    /**
     * Inserts a sale record into the database.
     *
     * @param session The Hibernate Session object.
     * @param sale    The Sale object representing the sale record.
     */
    private  void insertSaleRecord(Session session, Sale sale) {
        Book book = session.get(Book.class, sale.getBook().getBookID());
        Customer customer = session.get(Customer.class, sale.getCustomer().getCustomerID());
        if (book != null && customer != null) {
            Sale sale1 = sale.getSale(sale);
            session.persist(sale1);
        }
    }

    /**
     * Calculates the total revenue by genre and prints the results.
     */
    public  void calculateTotalRevenueByGenre() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String hql = "SELECT b.genre, SUM(s.totalPrice) FROM Sale s JOIN s.book b GROUP BY b.genre";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            List<Object[]> results = query.list();

            for (Object[] result : results) {
                String genre = (String) result[0];
                Double totalRevenue = (Double) result[1];
                System.out.println("Genre: " + genre + ", Total Revenue: $" + totalRevenue);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
