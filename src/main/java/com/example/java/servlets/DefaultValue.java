package com.example.java.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class DefaultValue {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customers customers = new Customers("Garnik","garnik@mail.com","Heraci 23/1",new Date());
        Customers customers1 = new Customers("Anna","anna@mail.com","Qochar 23/1",new Date());
        Customers customers2 = new Customers("Mary","mary@mail.com","Tumanyan 23/1",new Date());
        Customers customers3 = new Customers("Ashot","ashot@mail.com","Armenakyan 23/1",new Date());
        Customers customers4 = new Customers("Vaxinak","vaxinak@mail.com","Komitas 23/1",new Date());
        Customers customers5 = new Customers("Vazgenchik","Vazgenchik@mail.com","Spandaryan 23/1",new Date());
        session.persist(customers);
        session.persist(customers1);
        session.persist(customers2);
        session.persist(customers3);
        session.persist(customers4);
        session.persist(customers5);
        transaction.commit();
        session.close();
    }
}
