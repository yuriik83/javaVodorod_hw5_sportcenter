package org.example.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@org.hibernate.annotations.Where(clause = "premium = true")
public class PremiumClient extends Client {
}
