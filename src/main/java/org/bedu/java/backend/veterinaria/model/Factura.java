package org.bedu.java.backend.veterinaria.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date fecha_emision;

    @Column(nullable = false)
    @DecimalMin(value = "0.01")
    private float subtotal;

    @Column(nullable = false)
    @DecimalMin(value = "0.01")
    private float iva;

    @Column(nullable = false)
    @DecimalMin(value = "0.01")
    private float total;

    @Column(nullable = false, length = 13)
    private String rfc_cliente;

    @Column(nullable = false, length = 250)
    private String razon_social;

}
