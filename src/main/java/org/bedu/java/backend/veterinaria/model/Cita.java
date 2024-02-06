package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fechaCita;

    @Column(nullable = false)
    private LocalTime horaCita;

    @Column(nullable = false)
    private boolean primeraCita;

    @Column(nullable = false)
    private String motivoCita;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", referencedColumnName = "id")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "propietario_id", referencedColumnName = "id")
    private Propietario propietario;

}
