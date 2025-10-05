package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    private double rentPrice;

    public Room(String name, String identifier, int capacity, RoomStatus status, double rentPrice) {
        this.name = name;
        this.identifier = identifier;
        this.capacity = capacity;
        this.status = status;
        this.rentPrice = rentPrice;
    }
}

