package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String classification;

    @Column(nullable = false)
    private String description;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    @Range(min = 0)
    private int stock;

    @Column(nullable = false)
    @Range(min = 1)
    private float price;

    @Column(name = "usage_instructions", nullable = false)
    private String usageInstructions;

}
