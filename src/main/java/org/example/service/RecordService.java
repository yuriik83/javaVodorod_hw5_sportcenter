package org.example.service;

import config.HibernateConnection;
import org.example.entity.Record;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RecordService {

    public void add(Record record) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(record);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Record> getAll() {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.createQuery("from Record", Record.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Record getById(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.get(Record.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Record record = session.get(Record.class, id);
            if (record != null) {
                session.remove(record);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Record updatedRecord) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(updatedRecord);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Record> getByClientId(Long clientId) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Record r where r.client.id = :clientId",
                    Record.class
            ).setParameter("clientId", clientId).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Record> getByRoomId(Long roomId) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Record r where r.room.id = :roomId",
                    Record.class
            ).setParameter("roomId", roomId).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
