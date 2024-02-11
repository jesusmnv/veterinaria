package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class InvoiceMedicationKey {

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "medication_id")
    private Long medicationId;

}