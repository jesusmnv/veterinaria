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
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issuance_date", nullable = false)
    private LocalDate issuanceDate;

    @Column(nullable = false)
    @DecimalMin("0.01")
    private float subtotal;

    @Column(nullable = false)
    @DecimalMin("0.01")
    private float vat;

    @Column(nullable = false)
    @DecimalMin("0.01")
    private float total;

    @Column(name = "client_rfc", nullable = false, length = 13)
    private String clientRFC;

    @Column(name = "legal_name", nullable = false, length = 250)
    private String legalName;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

}
