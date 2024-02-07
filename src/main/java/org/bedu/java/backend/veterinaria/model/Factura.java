package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(nullable = false)
    @DecimalMin("0.01")
    private float subtotal;

    @Column(nullable = false)
    @DecimalMin("0.01")
    private float iva;

    @Column(nullable = false)
    @DecimalMin("0.01")
    private float total;

    @Column(name = "rfc_cliente", nullable = false, length = 13)
    private String rfcCliente;

    @Column(name = "razon_social", nullable = false, length = 250)
    private String razonSocial;

    @ManyToOne
    @JoinColumn(name = "propietario_id", referencedColumnName = "id")
    private Propietario propietario;

}