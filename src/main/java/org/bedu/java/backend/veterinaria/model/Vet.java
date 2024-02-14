package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "vet")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nameVet;

    @Column(nullable = false, length = 100)
    private String surnameVet;

    @Column(name = "maternal_surname", nullable = false, length = 100)
    private String maternalSurnameVet;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, length = 13)
    private String cellphone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String specialty;

    @Column(name = "entry_time", nullable = false)
    private LocalTime entryTime;

    @Column(name = "exit_time", nullable = false)
    private LocalTime exitTime;

}