package org.bedu.java.backend.veterinaria.dto.medication;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MedicationDTO {

    @Schema(description = "Medication ID", example = "100")
    private Long id;

    @Schema(description = "Medication name", example = "Doxycycline")
    private String name;

    @Schema(description = "Classification or type of medication", example = "Antibiotics")
    private String classification;

    @Schema(description = "Description of the medication", example = "Useful for treating diseases transmitted by ticks and respiratory illnesses.")
    private String description;

    @Schema(description = "Date when the medication expires", example = "2025-01-01")
    private LocalDate expirationDate;

    @Schema(description = "Quantity of medication in stock", example = "100")
    private int stock;

    @Schema(description = "Price of the medication", example = "125.50")
    private float price;

    @Schema(description = "Usage instructions for the medication", example = "Generally administered at a dosage of 2 to 5 mg per kg of body weight every 12 or 24 hours.")
    private String usageInstructions;


}