package org.bedu.java.backend.veterinaria.model;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "factura_has_medicamento")
public class FacturaMedicamento {

    @EmbeddedId
    private FacturaMedicamentoKey id;

    @ManyToOne
    @MapsId("facturaId")
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @ManyToOne
    @MapsId("medicamentoId")
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    @Column(nullable = false)
    @Range(min = 1)
    private int cantidad;

    @DecimalMin(value = "0.1")
    private float precio;

}