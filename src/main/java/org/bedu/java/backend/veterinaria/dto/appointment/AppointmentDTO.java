package org.bedu.java.backend.veterinaria.dto.appointment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.model.Veterinario;

@Data
public class AppointmentDTO {

    @Schema(description = "Appointment ID", example = "1")
    private Long id;

    @Schema(description = "Appointment date", example = "2021-10-31")
    private LocalDate appointmentDate;

    @Schema(description = "Appointment time", example = "12:00")
    private LocalTime appointmentTime;

    @Schema(description = "First Appointment", example = "Yes/No")
    private Boolean firstAppointment;

    @Schema(description = "Reason for the appointment", example = "Urgency")
    private String appointmentReason;

    private Veterinario vet;

    private Owner owner;

}