package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visitors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Visitor extends User {

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visit> visits;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> records;

    private LocalDateTime firstVisitDate;
    private LocalDateTime lastVisitDate;

    private double totalSpent;

    public enum Status {
        ACTIVE,
        INACTIVE,
        VIP
    }
}
