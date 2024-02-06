package org.bedu.java.backend.veterinaria.dto.cita;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

import org.bedu.java.backend.veterinaria.model.Propietario;
import org.bedu.java.backend.veterinaria.model.Veterinario;

@Data
public class UpdateCitaDTO {

    @Schema(description = "Fecha de la cita", example = "2021-10-31")

    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCita;

    @Schema(description = "Hora de la cita", example = "12:55")

    // @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaCita;

    @Schema(description = "Primera Cita", example = "Si/No")
    // @NotNull(message = "La primera cita no puede ser nula")
    // @BooleanFlag
    private boolean primeraCita;

    @Schema(description = "Motivo de la cita", example = "Urgencia")
    // @NotNull(message = "El motivo de la cita no puede ser nulo")
    private String motivoCita;

    private Veterinario veterinario;

    private Propietario propietario;

}