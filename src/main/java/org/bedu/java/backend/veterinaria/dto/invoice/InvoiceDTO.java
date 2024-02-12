package org.bedu.java.backend.veterinaria.dto.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.bedu.java.backend.veterinaria.model.Owner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    @Schema(description = "Invoice id", example = "11093")
    private Long id;

    @Schema(description = "Invoice date", example = "2019-10-23")
    private LocalDate issuanceDate;

    @Schema(description = "Subtotal of invoice", example = "123.50")
    private float subtotal;

    @Schema(description = "IVA of the invoice (16%)", example = "19.76")
    private float vat;

    @Schema(description = "Total of the invoice", example = "143.26")
    private float total;

    @Schema(description = "RFC of the customer", example = "HBO8912228A9")
    private String clientRFC;

    @Schema(description = "Company name", example = "Hortensia Bonilla Ortega")
    private String legalName;

    private Owner owner;

}