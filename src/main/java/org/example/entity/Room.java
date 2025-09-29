package org.example.entity;

import jakarta.persistence.*;
import org.example.entity.RoomStatus;

@Entity
@Table(name = "rooms")
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

    public Room() {}

    public Room(String name, String identifier, int capacity, RoomStatus status, double rentPrice) {
        this.name = name;
        this.identifier = identifier;
        this.capacity = capacity;
        this.status = status;
        this.rentPrice = rentPrice;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }
    public double getRentPrice() { return rentPrice; }
    public void setRentPrice(double rentPrice) { this.rentPrice = rentPrice; }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", capacity=" + capacity +
                ", status=" + status +
                ", rentPrice=" + rentPrice +
                '}';
    }
}
