package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT r.id, r.name, r.capacity, r.pricePerHour FROM rooms r WHERE r.capacity <= 15")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SmallRoom {

    @Id
    private Long id;

    private String name;
    private int capacity;
    private double priceByHour;
}
