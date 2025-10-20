package org.example.repository;

import config.HibernateConnection;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ServiceRepository {

    private final SessionFactory sessionFactory;

    public ServiceRepository() {
        this.sessionFactory = HibernateConnection.getSessionFactory();
    }

    public Service getActivityWithMinPrice() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Service> cq = cb.createQuery(Service.class);
            Root<Service> root = cq.from(Service.class);
            cq.select(root);
            cq.orderBy(cb.asc(root.get("price")));
            return session.createQuery(cq).setMaxResults(1).uniqueResult();
        }
    }
}

