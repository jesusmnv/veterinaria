package org.bedu.java.backend.veterinaria.dto.appointment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateAppointmentDTO {

    @Schema(description = "Appointment date", example = "2021-10-31")
    @NotNull(message = "Appointment date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @Schema(description = "Appointment time", example = "12:00")
    @NotNull(message = "Appointment time cannot be null")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime appointmentTime;

    @Schema(description = "First Appointment", example = "Yes/No")
    @NotNull(message = "First appointment cannot be null")
    private Boolean firstAppointment;

    @Schema(description = "Reason for the appointment", example = "Urgency")
    @NotNull(message = "Appointment reason cannot be null")
    private String appointmentReason;

    private Vet vet;

    private Owner owner;


}