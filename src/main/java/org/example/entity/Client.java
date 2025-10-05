package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Session;
import util.HibernateUtil;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private LocalDate lastVisitDate;
    private boolean premium;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Status status;

    private double totalSpent;

    public enum Status {
        ACTIVE,
        BLOCKED,
        PREMIUM
    }

    public Client(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Client findClientById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Client.class, id);
        }
    }
}
