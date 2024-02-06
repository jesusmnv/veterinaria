package org.bedu.java.backend.veterinaria.dto.consulta;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.model.Veterinario;

import java.util.Date;

@Data
public class UpdateConsultaDTO {

    @Schema(description = "Fecha de la consulta", example = "2023-11-25")
    private Date fechaConsulta;

    @Schema(description = "Diagnóstico de la consulta", example = "Fiebre y tos")
    private String diagnostico;

    @Schema(description = "Tratamiento indicado", example = "Tomar medicamento X cada 8 horas")
    private String tratamientoIndicado;

    @Schema(description = "Observaciones adicionales", example = "Seguimiento requerido")
    private String observaciones;

    // Relación
    private Mascota mascota;

    private Veterinario veterinario;

}