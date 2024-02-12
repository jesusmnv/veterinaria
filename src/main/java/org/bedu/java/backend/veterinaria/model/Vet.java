package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String name;

    @Column(name = "p_last_name", nullable = false, length = 100)
    private String pLastName;

    @Column(name = "m_last_name", nullable = false, length = 100)
    private String mLastName;

    @Column(name = "birdDate", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 13)
    private String cellPhone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String specialty;

    @Column(name = "entry_time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime entryTime;

    @Column(name = "exit_time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime exitTime;

}