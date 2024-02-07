package org.bedu.java.backend.veterinaria.dto.factura;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import org.bedu.java.backend.veterinaria.model.Propietario;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {

    @Schema(description = "Identificador de la factura", example = "11093")
    private Long id;

    @Schema(description = "Fecha de la factura", example = "2019-10-23")
    private Date fechaEmision;

    @Schema(description = "Subtotal de la factura", example = "123.50")
    private float subtotal;

    @Schema(description = "IVA de la factura (16%)", example = "19.76")
    private float iva;

    @Schema(description = "Total de la factura", example = "143.26")
    private float total;

    @Schema(description = "RFC del cliente", example = "HBO8912228A9")
    private String rfcCliente;

    @Schema(description = "Razón social", example = "Hortensia Bonilla Ortega")
    private String razonSocial;

    private Propietario propietario;

}