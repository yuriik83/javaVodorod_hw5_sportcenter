package org.example.repository;

import config.HibernateConnection;
import jakarta.persistence.EntityManager;
import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public Employee getHighestPaidEmployee() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery(
                    "FROM Employee e ORDER BY e.salary DESC", Employee.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }

    public Employee getLowestPaidEmployee() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery(
                    "FROM Employee e ORDER BY e.salary ASC", Employee.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }

    public BigDecimal calculateTotalSalaryExpenses(LocalDate start, LocalDate end) {
        try (Session session = sessionFactory.openSession()) {
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();

            BigDecimal total = BigDecimal.ZERO;

            for (Employee e : employees) {
                LocalDate hireDate = e.getHireDate();
                LocalDate fireDate = e.getFireDate() != null ? e.getFireDate() : LocalDate.now();

                if (hireDate.isAfter(end) || fireDate.isBefore(start)) {
                    continue;
                }

                LocalDate actualStart = hireDate.isAfter(start) ? hireDate : start;
                LocalDate actualEnd = fireDate.isBefore(end) ? fireDate : end;

                long monthsWorked = ChronoUnit.MONTHS.between(actualStart.withDayOfMonth(1), actualEnd.withDayOfMonth(1)) + 1;
                if (monthsWorked < 0) monthsWorked = 0;

                total = total.add(e.getSalary().multiply(BigDecimal.valueOf(monthsWorked)));
            }

            return total;
        }
    }

}
