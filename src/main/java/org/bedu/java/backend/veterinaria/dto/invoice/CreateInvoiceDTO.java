package org.bedu.java.backend.veterinaria.dto.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.bedu.java.backend.veterinaria.model.Owner;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreateInvoiceDTO {

    @Schema(description = "Invoice date", example = "2019-10-23")
    @NotNull(message = "Invoice date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuanceDate;

    @Schema(description = "Invoice subtotal", example = "123.50")
    @DecimalMin(value = "0.1", message = "Invoice subtotal should be greater than zero")
    private float subtotal;

    @Schema(description = "VAT of the invoice (16%)", example = "19.76")
    @DecimalMin(value = "0.1", message = "IVA should be greater than zero")
    private float vat;

    @Schema(description = "Total of the invoice", example = "143.26")
    private float total;

    @Schema(description = "RFC of the client", example = "HBO8912228A9")
    @NotBlank(message = "RFC is mandatory")
    @Length(min = 13, max = 13, message = "RFC should have 13 chars")
    private String clientRFC;

    @Schema(description = "Company name", example = "Hortensia Bonilla Ortega")
    @NotBlank(message = "The company name is mandatory")
    @Length(min = 1, max = 250, message = "The company name must not exceed 250 chars")
    private String legalName;

    private Owner owner;

}