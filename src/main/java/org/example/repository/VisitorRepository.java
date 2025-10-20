package org.example.repository;

import config.HibernateConnection;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.entity.Room;
import org.example.entity.Visit;
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

    public List<Room> findRoomsVisitedByOlderGuests(int ageThreshold) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Room> cq = cb.createQuery(Room.class);

            Root<Visit> visitRoot = cq.from(Visit.class);
            Join<Visit, Visitor> visitorJoin = visitRoot.join("visitor");
            Join<Visit, Room> roomJoin = visitRoot.join("room");

            cq.select(roomJoin).distinct(true)
                    .where(cb.gt(visitorJoin.get("age"), ageThreshold));

            return session.createQuery(cq).getResultList();
        }
    }
}

