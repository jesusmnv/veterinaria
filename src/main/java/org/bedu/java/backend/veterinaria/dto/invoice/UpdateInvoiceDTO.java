package org.bedu.java.backend.veterinaria.dto.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

import org.bedu.java.backend.veterinaria.model.Owner;

@Data
public class UpdateInvoiceDTO {

    @Schema(description = "Invoice date", example = "2019-10-23")
    private LocalDate issuanceDateU;

    @Schema(description = "Subtotal of the invoice", example = "123.50")
    private float subtotalU;

    @Schema(description = "IVA of the invoice (16%)", example = "19.76")
    private float vatU;

    @Schema(description = "Total of the invoice", example = "143.26")
    private float totalU;

    @Schema(description = "RFC of the customer", example = "HBO8912228A9")
    private String clientRFCU;

    @Schema(description = "Company name", example = "Hortensia Bonilla Ortega")
    private String legalNameU;

    private Owner ownerU;

}