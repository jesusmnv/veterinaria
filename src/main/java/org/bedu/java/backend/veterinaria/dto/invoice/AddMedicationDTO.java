package org.bedu.java.backend.veterinaria.dto.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddMedicationDTO {
    @Schema(description = "Identifier of the medication to associate", example = "10")
    private Long medicationId;
    private float price;
    private int quantity;
}