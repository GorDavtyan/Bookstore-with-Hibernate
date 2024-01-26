package com.picsart;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BooksManagement {

    /**
     * Updates the details of a book in the database.
     *
     * @param book The Book object containing the updated details.
     */
    public  void updateBookDetails(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();


             Book book1 = session.get(Book.class, book.getBookID());
            if (book1 != null) {
                book1 = book.getBook(book);
                session.merge(book1);
                transaction.commit();
                System.out.println("Book details updated successfully.");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    /**
     * Searches for books in the database based on the specified genre or author.
     *
     * @param searchBy    The search criteria ('genre' or 'author').
     * @param searchValue The value to search for in the specified criteria.
     */
    public  void searchBooksByGenreOrAuthor(String searchBy, String searchValue) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){

            String hql = "FROM Book WHERE " + searchBy + " = :searchValue";

            Query<Book> query = session.createQuery(hql, Book.class);
            query.setParameter("searchValue", searchValue);
            List<Book> books = query.list();
            if (!books.isEmpty()) {
                System.out.println("Books found:");
                for (Book book : books) {

                    System.out.println(book);
                }

            } else {
                System.out.println("No books found with the given " + searchBy + ".");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
