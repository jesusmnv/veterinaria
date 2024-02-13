package org.bedu.java.backend.veterinaria.dto.pet;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import org.bedu.java.backend.veterinaria.model.Owner;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdatePetDTO {

    @Schema(description = "Pet's name", example = "Rex")
    @NotBlank(message = "The pet's name is mandatory")
    @Length(min = 1, max = 100, message = "Pet's name cannot exceed 100 chars")
    private String nameU;

    @Schema(description = "Pet species", example = "Parrot")
    @NotBlank(message = "Species is mandatory")
    @Length(min = 1, max = 40, message = "Species description cannot exceed 40 chars")
    private String speciesU;

    @Schema(description = "Pet breed", example = "Eclectus Parrot")
    @NotBlank(message = "Breed is mandatory")
    @Length(min = 1, max = 60, message = "Breed description cannot exceed 60 chars")
    private String breedU;

    @Schema(description = "Pet's age", example = "1")
    @Min(value = 1, message = "Pet's age must be greater than zero")
    private int ageU;

    @Schema(description = "Pet height in meters", example = "0.5")
    @DecimalMin(value = "0.1", message = "Pet height must be greater than zero")
    private float heightU;

    @Schema(description = "Pet weight in Kg", example = "0.3")
    @DecimalMin(value = "0.1", message = "Weight must be greater than zero")
    private float weightU;

    @Schema(description = "Pet gender (Male/Female)", example = "Male")
    @NotBlank(message = "Please enter pet's gender")
    @Length(min = 1, max = 20, message = "Gender description must be between 1 and 20 chars")
    private String genderU;

    @Schema(description = "Pet color", example = "Black with white stripes")
    @NotBlank(message = "Pet color is required")
    @Length(min = 1, max = 50, message = "Color description must be between 1 and 50 chars")
    private String colorU;

    private Owner ownerU;

}