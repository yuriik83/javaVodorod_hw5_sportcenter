package org.example.repository;

import config.HibernateConnection;
import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeRepository {

    private final SessionFactory sessionFactory;

    public EmployeeRepository() {
        this.sessionFactory = HibernateConnection.getSessionFactory();
    }

    public void add(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
        }
    }

    public List<Employee> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }

    public Employee getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.remove(employee);
            }
            session.getTransaction().commit();
        }
    }
}
