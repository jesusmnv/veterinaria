package org.bedu.java.backend.veterinaria.dto.consulta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.model.Veterinario;
import org.springframework.format.annotation.DateTimeFormat;


@Data
public class CreateConsultaDTO {

    @Schema(description = "Fecha de la consulta", example = "2023-11-25")
    @NotNull(message = "La fecha de la consulta no puede ser nula") //Verificar
    @DateTimeFormat(pattern = "yyyy-MM-dd") //Verificar
    private LocalDate fechaConsulta;

    @Schema(description = "Diagnóstico de la consulta", example = "Fiebre y tos")
    @NotBlank(message = "El diagnóstico es obligatorio")
    private String diagnostico;

    @Schema(description = "Tratamiento indicado", example = "Tomar medicamento X cada 8 horas")
    @NotBlank(message = "El tratamiento indicado es obligatorio")
    private String tratamientoIndicado;

    @Schema(description = "Observaciones adicionales", example = "Seguimiento requerido")
    @NotBlank(message = "Las observaciones son obligatorias")
    private String observaciones;

    // Relación
    private Mascota mascota;

    private Veterinario veterinario;

}