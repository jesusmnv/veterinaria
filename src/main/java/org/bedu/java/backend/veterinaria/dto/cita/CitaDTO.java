package org.bedu.java.backend.veterinaria.dto.cita;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
public class CitaDTO {

    @Schema(description = "ID de la cita", example = "1")
    private Long id;

    @Schema(description = "Fecha de la cita", example = "2021-10-31")
    private Date fechaCita;

    @Schema(description = "Hora de la cita", example = "12:55")
    private Time horaCita;

    @Schema(description = "Primera Cita", example = "Si/No") // Preguntar True / False
    private boolean primeraCita;

    @Schema(description = "Motivo de la cita", example = "Urgencia")
    private String motivoCita;

}