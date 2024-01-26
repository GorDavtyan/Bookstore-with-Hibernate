package com.picsart;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class SalesReports {

    /**
     * Generates a report on books sold, including book title, customer name, and sale date.
     */
    public  void generateBooksReport() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String hql = "SELECT b.title, c.name, s.dateOfSale," +
                    " SUM(s.totalPrice) " +
                    "FROM Sale s " +
                    "JOIN s.book b " +
                    "JOIN s.customer c " +
                    "GROUP BY b.title, c.name, s.dateOfSale";

            Query<Object[]> query = session.createQuery(hql, Object[].class);
            List<Object[]> results = query.list();

            for (Object[] result : results) {
                String bookTitle = (String) result[0];
                String customerName = (String) result[1];
                Date saleDate = (Date) result[2];

                System.out.println("Book Title: " + bookTitle +
                        ", Customer Name: " + customerName +
                        ", Sale Date: " + saleDate.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Generates a revenue report by genre, summarizing the total revenue for each genre.
     */
    public  void generateRevenueReportByGenre() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){

            String hql = "SELECT b.genre, SUM(s.totalPrice) " +
                    "FROM Book b " +
                    "JOIN b.sales s  " +
                    "GROUP BY b.genre";

            Query<Object[]> query = session.createQuery(hql, Object[].class);
            List<Object[]> results = query.list();

            for (Object[] result : results) {
                String genre = (String) result[0];
                double totalRevenue = (double) result[1];

                System.out.println("Genre: " + genre + ", Total Revenue: $" + totalRevenue);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
