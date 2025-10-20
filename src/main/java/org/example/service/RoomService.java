package org.example.service;

import config.HibernateConnection;
import org.example.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class RoomService {

    public void add(Room room) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(room);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAll() {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.createQuery("from Room", Room.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Room getById(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.get(Room.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Room room = session.get(Room.class, id);
            if (room != null) {
                session.remove(room);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Room updatedRoom) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(updatedRoom);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getCostPerHourPerPerson(Long roomId) {
        Room room = getById(roomId);
        if (room == null) return BigDecimal.ZERO;
        int capacity = room.getCapacity();
        if (capacity <= 0) return BigDecimal.ZERO;

        BigDecimal rent = BigDecimal.valueOf(room.getRentPrice());
        return rent.divide(BigDecimal.valueOf(capacity), 2, BigDecimal.ROUND_HALF_UP);
    }

}

