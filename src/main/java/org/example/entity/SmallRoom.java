package org.example.entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import jakarta.persistence.Entity;

@Entity
@Immutable
@Subselect("SELECT r.id, r.name, r.capacity, r.pricePerHour FROM rooms r WHERE r.capacity <= 15")
public class SmallRoom {
    @jakarta.persistence.Id
    private Long id;

    private String name;
    private int capacity;
    private double priceByHour;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPriceByHour() {
        return priceByHour;
    }

    public void setPriceByHour(double priceByHour) {
        this.priceByHour = priceByHour;
    }
}
