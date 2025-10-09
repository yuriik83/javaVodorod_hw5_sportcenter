package org.example.repository;

import config.HibernateConnection;
import org.example.entity.Visitor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class VisitorRepository {

    private final SessionFactory sessionFactory;

    public VisitorRepository() {
        this.sessionFactory = HibernateConnection.getSessionFactory();
    }

    public void add(Visitor visitor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(visitor);
            session.getTransaction().commit();
        }
    }

    public List<Visitor> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Visitor", Visitor.class).list();
        }
    }
}

