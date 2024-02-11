package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invoice_medication")
public class InvoiceMedication {

    @EmbeddedId
    private InvoiceMedicationKey id;

    @ManyToOne
    @MapsId("invoiceId")
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @MapsId("medicationId")
    @JoinColumn(name = "medication_id")
    private Medication medication;

    private float price;

    private int quantity;

}