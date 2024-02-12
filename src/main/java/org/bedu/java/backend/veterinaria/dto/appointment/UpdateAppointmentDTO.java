package org.bedu.java.backend.veterinaria.dto.appointment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UpdateAppointmentDTO {

    @Schema(description = "Appointment date", example = "2021-10-31")
    @NotNull(message = "Appointment date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDateU;

    @Schema(description = "Appointment time", example = "12:00")
    @NotNull(message = "Appointment time cannot be null")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime appointmentTimeU;

    @Schema(description = "First Appointment", example = "Yes/No")
    @NotNull(message = "First appointment cannot be null")
    private Boolean firstAppointmentU;

    @Schema(description = "Reason for the appointment", example = "Urgency")
    @NotNull(message = "Appointment reason cannot be null")
    private String appointmentReasonU;

    private Vet vetU;

    private Owner ownerU;

}