package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;
}
