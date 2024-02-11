package org.bedu.java.backend.veterinaria.dto.consultation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.model.Vet;

import java.time.LocalDate;

@Data
public class ConsultationDTO {

    @Schema(description = "Consultation ID", example = "1")
    private Long id;

    @Schema(description = "Consultation date", example = "2023-11-25")
    private LocalDate consultationDate;

    @Schema(description = "Consultation diagnosis", example = "Fever and cough")
    private String diagnosis;

    @Schema(description = "Prescribed treatment", example = "Take medication X every 8 hours")
    private String prescribedTreatment;

    @Schema(description = "Additional observations", example = "Follow-up required")
    private String observations;

    // Relación
    private Pet pet;

    private Vet vet;

}