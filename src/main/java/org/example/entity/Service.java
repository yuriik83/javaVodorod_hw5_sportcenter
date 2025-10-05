package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }
}