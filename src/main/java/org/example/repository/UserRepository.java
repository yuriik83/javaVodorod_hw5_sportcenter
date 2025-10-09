package org.example.repository;

import config.HibernateConnection;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepository() {
        this.sessionFactory = HibernateConnection.getSessionFactory();
    }

    public void add(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public User getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) session.remove(user);
            session.getTransaction().commit();
        }
    }
}