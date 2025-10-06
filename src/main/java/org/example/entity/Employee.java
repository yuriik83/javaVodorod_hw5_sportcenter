package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {

    private LocalDate hireDate;
    private LocalDate fireDate;
    private String position;
    private double monthlySalary;
}
