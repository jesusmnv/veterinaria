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

    @Schema(description = "Identificador de la factura", example = "11093")
    private Long id;

    @Schema(description = "Fecha de la factura", example = "2019-10-23")
    private LocalDate issuanceDate;

    @Schema(description = "Subtotal de la factura", example = "123.50")
    private float subtotal;

    @Schema(description = "IVA de la factura (16%)", example = "19.76")
    private float vat;

    @Schema(description = "Total de la factura", example = "143.26")
    private float total;

    @Schema(description = "RFC del cliente", example = "HBO8912228A9")
    private String clientRFC;

    @Schema(description = "Razón social", example = "Hortensia Bonilla Ortega")
    private String legalName;

    private Owner owner;

}