package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
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
    private Time horaCita;

    @Column(nullable = false)
    private boolean primeraCita;

    @Column(nullable = false)
    private String motivoCita;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", referencedColumnName = "id")
    Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "propietario_id", referencedColumnName = "id")
    Propietario propietario;

}
