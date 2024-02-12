package org.bedu.java.backend.veterinaria.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate consultationDate;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String prescribedTreatment;

    @Column(nullable = false)
    private String observations;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
    private Vet vet;

}