package org.bedu.java.backend.veterinaria.dto.consultation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UpdateConsultationDTO {

    @Schema(description = "Consultation date", example = "2023-11-25")
    @NotNull(message = "Consultation date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate consultationDateU;

    @Schema(description = "Consultation diagnosis", example = "Fever and cough")
    @NotBlank(message = "Diagnosis is mandatory")
    private String diagnosisU;

    @Schema(description = "Prescribed treatment", example = "Take medication X every 8 hours")
    @NotBlank(message = "Prescribed treatment is mandatory")
    private String prescribedTreatmentU;

    @Schema(description = "Additional observations", example = "Follow-up required")
    @NotBlank(message = "Observations are mandatory")
    private String observationsU;

    // Relación
    private Pet petU;

    private Vet vetU;

}