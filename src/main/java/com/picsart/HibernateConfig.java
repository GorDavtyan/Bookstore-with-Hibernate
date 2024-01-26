package com.picsart;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    private static StandardServiceRegistry standardServiceRegistry;


    /**
     * Retrieves the Hibernate SessionFactory. If the SessionFactory is not initialized,
     * it creates a new instance based on the configuration.
     *
     * @return The Hibernate SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)

            try {
                standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();


                Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();


                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                System.out.println(e.getMessage());

                if (standardServiceRegistry != null) {
                    StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
                }
            }
        return sessionFactory;
    }

    /**
     * Shuts down the Hibernate configuration, destroying the StandardServiceRegistry.
     * This method should be called when the application is shutting down.
     */
    public static void shutDown() {
        if (standardServiceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
        }
    }
}
