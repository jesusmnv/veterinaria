package org.bedu.java.backend.veterinaria.dto.medication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UpdateMedicationDTO {

    @Schema(description = "Medication name", example = "Doxycycline")
    @NotBlank(message = "The medication name is mandatory")
    @Length(min = 1, max = 100, message = "The medication name cannot exceed 100 characters")
    private String name;

    @Schema(description = "Classification or type of medication", example = "Antibiotics")
    @NotBlank(message = "The medication classification is mandatory")
    @Length(min = 1, max = 100, message = "The classification cannot exceed 75 characters")
    private String classification;

    @Schema(description = "Description of the medication", example = "Useful for treating diseases transmitted by ticks and respiratory illnesses.")
    @NotBlank(message = "The medication description is mandatory")
    private String description;

    @Schema(description = "Date when the medication expires", example = "2025-01-01")
    @NotNull(message = "The medication expiration date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    @Schema(description = "Quantity of medication in stock", example = "100")
    @Min(value = 1, message = "The medication stock must be greater than or equal to 1")
    private int stock;

    @Schema(description = "Price of the medication", example = "125.50")
    @DecimalMin(value = "0.0", message = "The medication price must be greater than or equal to 0.0")
    private float price;

    @Schema(description = "Usage instructions for the medication", example = "Generally administered at a dosage of 2 to 5 mg per kg of body weight every 12 or 24 hours.")
    @NotBlank(message = "The medication usage instructions are mandatory")
    private String usageInstructions;


}