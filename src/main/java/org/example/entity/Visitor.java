package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Visitor extends User {

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime firstVisitDate;
    private LocalDateTime lastVisitDate;

    private double totalSpent;

    public enum Status {
        ACTIVE,
        INACTIVE,
        VIP
    }
}
