package org.example.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private String city;
    private String street;
    private String houseNumber;
    private String postalCode;
}
