package org.bedu.java.backend.veterinaria.dto.factura;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddMedicamentoDTO {
    @Schema(description = "Identificador del medicamento a asociar", example = "10")
    private Long medicamentoId;
    private float precio;
    private int cantidad;
}