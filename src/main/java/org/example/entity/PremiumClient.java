package org.example.entity;

import jakarta.persistence.DiscriminatorValue;
import org.hibernate.annotations.Where;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
@Where(clause = "premium = true")
public class PremiumClient extends Client {}