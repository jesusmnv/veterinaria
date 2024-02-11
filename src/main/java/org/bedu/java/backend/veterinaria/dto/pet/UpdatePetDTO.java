package org.bedu.java.backend.veterinaria.dto.pet;

import org.bedu.java.backend.veterinaria.model.Owner;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePetDTO {

    @Schema(description = "Pet name", example = "Rex")
    @NotBlank
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

    private Owner ownerU;

}