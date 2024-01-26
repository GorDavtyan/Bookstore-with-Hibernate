package com.picsart;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomersManagement {

    /**
     * Updates the information of a customer in the database.
     *
     * @param customer The Customer object containing the updated information.
     */
    public  void updateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Customer customer1 = session.get(Customer.class, customer.getCustomerID());
            if (customer1 != null) {
               customer1 = customer.getCustomer(customer);

                session.merge(customer1);
                transaction.commit();
                System.out.println("Customer information updated successfully.");
            } else {
                System.out.println("No customer found with the given ID.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * Views the purchase history for a customer based on the customer ID.
     *
     * @param customerID The ID of the customer whose purchase history is to be viewed.
     */
    public  void viewCustomerPurchaseHistory(int customerID) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String hql = "FROM Sale WHERE customer.customerID = : customerID";
            Query<Sale> query = session.createQuery(hql, Sale.class);
            query.setParameter("customerID", customerID);
            List<Sale> sales = query.list();
            if (!sales.isEmpty()) {
                System.out.println("Purchase history for customer with ID " + customerID + ":");
                for (Sale sale : sales) {
                    System.out.println(sale);
                }
            } else {
                System.out.println("No purchase history found for the customer with ID " + customerID + ".");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
