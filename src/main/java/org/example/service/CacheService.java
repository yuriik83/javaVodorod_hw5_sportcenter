package org.example.service;

import config.HibernateConnection;
import org.example.entity.Activity;
import org.example.entity.Employee;
import org.example.repository.ActivityRepository;
import org.example.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CacheService {

    private final ActivityRepository activityRepo = new ActivityRepository();
    private final EmployeeRepository employeeRepo = new EmployeeRepository();
    private final SessionFactory sessionFactory = HibernateConnection.getSessionFactory();

    public void runQueries() {
        try (Session session = sessionFactory.openSession()) {
            System.out.println("все активности: ");
            List<Activity> activities = activityRepo.getAllActivities();

            System.out.println("активность по id:");
            Activity activity1 = activityRepo.getActivityById(1L);
        }

        try (Session session = sessionFactory.openSession()) {
            System.out.println("активность по id (новая сессия):");
            Activity activity2 = activityRepo.getActivityById(1L);
        }
    }
}

