package org.bedu.java.backend.veterinaria.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "propietario")
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 70)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 70)
    private String apellidoMaterno;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(nullable = false, length = 15)
    private String celular;

    @Column(nullable = false, length = 150)
    private String correo;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false, length = 60)
    private String ocupacion;

    @ManyToOne
    @JoinColumn(name = "factura_id", referencedColumnName = "id")
    Factura factura;

}
