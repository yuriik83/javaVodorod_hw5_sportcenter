package org.example.repository;

import config.HibernateConnection;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RoomRepository {

    private final SessionFactory sessionFactory;

    public RoomRepository() {
        this.sessionFactory = HibernateConnection.getSessionFactory();
    }

    public Long getTotalCapacity() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Room> root = cq.from(Room.class);
            cq.select(cb.sum(root.get("capacity")));

            return session.createQuery(cq).getSingleResult();
        }
    }
}

