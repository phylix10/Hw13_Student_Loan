package org.example.configuration;


import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(Card.class)
                    .addAnnotatedClass(HousingInformation.class)
                    .addAnnotatedClass(Installments.class)
                    .addAnnotatedClass(Loan.class)
                    .addAnnotatedClass(SpouseInformation.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}
