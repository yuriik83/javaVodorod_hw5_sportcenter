package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private LocalDate lastVisitDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private double totalSpent;

    public enum Status {
        ACTIVE,
        BLOCKED,
        PREMIUM
    }

    public Client() {}

    public Client(String firstName, String lastName, int age, String phoneNumber,
                  LocalDate lastVisitDate, Status status, double totalSpent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.lastVisitDate = lastVisitDate;
        this.status = status;
        this.totalSpent = totalSpent;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
