package org.bedu.java.backend.veterinaria.dto.cita;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
public class CreateCitaDTO {

    @Schema(description = "Fecha de la cita", example = "2021-10-31")
    @NotNull(message = "La fecha de la cita no puede ser nula")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCita;

    @Schema(description = "Hora de la cita", example = "12:55")
    @NotNull(message = "La fecha de la cita no puede ser nula")
    @DateTimeFormat(pattern = "HH:mm")
    private Time horaCita;

    @Schema(description = "Primera Cita", example = "Si/No")
    @NotNull(message = "La primera cita no puede ser nula")
    @BooleanFlag
    private boolean primeraCita;

    @Schema(description = "Motivo de la cita", example = "Urgencia")
    @NotNull(message = "El motivo de la cita no puede ser nulo")
    private String motivoCita;

}