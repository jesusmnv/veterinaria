package org.bedu.java.backend.veterinaria.dto.mascota;

import org.bedu.java.backend.veterinaria.model.Owner;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePetDTO {

    @Schema(description = "Pet name", example = "Rex")
    @NotBlank
    private String name;

    @Schema(description = "Species pet", example = "Parrot")
    private String species;

    @Schema(description = "Pet breed", example = "Eclectus Parrot")
    private String breed;

    @Schema(description = "Pet's age", example = "1")
    private int age;

    @Schema(description = "Pet's height in meters", example = "0.5")
    private float height;

    @Schema(description = "Pet's weight in Kg", example = "0.3")
    private float weight;

    @Schema(description = "Pet gender (Male/Female)", example = "Male")
    private String gender;

    @Schema(description = "Pet color", example = "Black with white stripes")
    private String color;

    private Owner owner;

}