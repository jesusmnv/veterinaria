package org.bedu.java.backend.veterinaria.dto.pet;

import org.bedu.java.backend.veterinaria.model.Owner;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePetDTO {

    @Schema(description = "Pet name", example = "Rex")
    @NotBlank
<<<<<<< Updated upstream
    @Length(min = 1, max = 100, message = "Pet's name cannot exceed 100 chars")
    private String name;

    @Schema(description = "Species pet", example = "Parrot")
    @Length(min = 1, max = 40, message = "Species description cannot exceed 40 chars")
    private String species;

    @Schema(description = "Pet breed", example = "Eclectus Parrot")
    @Length(min = 1, max = 60, message = "Breed description cannot exceed 60 chars")
    private String breed;

    @Schema(description = "Pet's age", example = "1")
    @Min(value = 1, message = "Pet's age must be greater than zero")
    private int age;

    @Schema(description = "Pet's height in meters", example = "0.5")
    @DecimalMin(value = "0.1", message = "Pet height must be greater than zero")
    private float height;

    @Schema(description = "Pet's weight in Kg", example = "0.3")
    @DecimalMin(value = "0.1", message = "Weight must be greater than zero")
    private float weight;

    @Schema(description = "Pet gender (Male/Female)", example = "Male")
    @Length(min = 1, max = 20, message = "Gender description must be between 1 and 20 chars")
    private String gender;

    @Schema(description = "Pet color", example = "Black with white stripes")
    @Length(min = 1, max = 50, message = "Color description must be between 1 and 50 chars")
    private String color;
=======
    private String nameU;

    @Schema(description = "Species pet", example = "Parrot")
    private String speciesU;

    @Schema(description = "Pet breed", example = "Eclectus Parrot")
    private String breedU;

    @Schema(description = "Pet's age", example = "1")
    private int ageU;

    @Schema(description = "Pet's height in meters", example = "0.5")
    private float heightU;

    @Schema(description = "Pet's weight in Kg", example = "0.3")
    private float weightU;

    @Schema(description = "Pet gender (Male/Female)", example = "Male")
    private String genderU;

    @Schema(description = "Pet color", example = "Black with white stripes")
    private String colorU;
>>>>>>> Stashed changes

    private Owner ownerU;

}