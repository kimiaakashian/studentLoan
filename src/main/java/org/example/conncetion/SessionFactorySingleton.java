package org.example.conncetion;


import org.example.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Bank.class)
                    .addAnnotatedClass(BankAccount.class)
                    .addAnnotatedClass(City.class)
                    .addAnnotatedClass(EducationGrade.class)
                    .addAnnotatedClass(LoanRequest.class)
                    .addAnnotatedClass(LoanInstallment.class)
                    .addAnnotatedClass(LoanLimit.class)
                    .addAnnotatedClass(LoanType.class)
                    .addAnnotatedClass(University.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}