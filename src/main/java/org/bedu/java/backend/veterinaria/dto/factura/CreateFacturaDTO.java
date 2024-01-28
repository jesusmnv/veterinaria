package org.bedu.java.backend.veterinaria.dto.factura;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public class CreateFacturaDTO {

    @Schema(description = "Fecha de la factura", example = "2019-10-23")
    @NotBlank(message = "La fecha de la factura es obligatoria")
    private Date fecha_emision;

    @Schema(description = "Subtotal de la factura", example = "123.50")
    @DecimalMin(value = "0.1", message = "El subtotal debe ser mayor a cero")
    private float subtotal;

    @Schema(description = "IVA de la factura (16%)", example = "19.76")
    @DecimalMin(value = "0.1", message = "El IVA debe ser mayor a cero")
    private float iva;

    @Schema(description = "Total de la factura", example = "143.26")
    @DecimalMin(value = "0.1", message = "El Total debe ser mayor a cero")
    private float total;

    @Schema(description = "RFC del cliente", example = "HBO8912228A9")
    @NotBlank(message = "El RFC es obligatorio")
    @Length(min = 13, max = 13, message = "El RFC debe tener 13 caracteres")
    private String rfc_cliente;

    @Schema(description = "Razón social", example = "Hortensia Bonilla Ortega")
    @NotBlank(message = "La razón social es obligatoria")
    @Length(min = 1, max = 250, message = "La razón social no puede exceder de 250 caracteres")
    private String razon_social;

}
