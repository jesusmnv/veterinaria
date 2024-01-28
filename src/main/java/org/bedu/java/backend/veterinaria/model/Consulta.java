package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fechaConsulta;

    @Column(nullable = false)
    private String diagnostico;

    @Column(nullable = false)
    private String tratamientoIndicado;

    @Column(nullable = false)
    private String observaciones;

    @ManyToOne
    private Mascota mascota;

    @ManyToOne
    private Veterinario veterinario;

}