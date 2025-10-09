package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String identifier;

    private int capacity;

    private double pricePerHour;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> records;

    private double rentPrice;

    public Room(String name, String identifier, int capacity, RoomStatus status, double rentPrice) {
        this.name = name;
        this.identifier = identifier;
        this.capacity = capacity;
        this.status = status;
        this.rentPrice = rentPrice;
    }
}