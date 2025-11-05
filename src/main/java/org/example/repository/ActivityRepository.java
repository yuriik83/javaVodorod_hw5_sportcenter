package org.example.repository;

import config.HibernateConnection;
import org.example.entity.Activity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ActivityRepository {

    private final SessionFactory sessionFactory = HibernateConnection.getSessionFactory();

    public List<Activity> getAllActivities() {
        try (Session session = sessionFactory.openSession()) {
            Query<Activity> query = session.createQuery("FROM Activity", Activity.class);
            query.setCacheable(true);
            return query.list();
        }
    }

    public Activity getActivityById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Activity.class, id);
        }
    }
}
