package org.bedu.java.backend.veterinaria.dto.vet;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateVetDTO {

    @Schema(description = "Vet name", example = "Alberto")
    @NotBlank
    private String name;

    @Schema(description = "Vet paternal last name", example = "Juarez")
    @NotBlank
    private String pLastName;

    @Schema(description = "Vet maternal last name", example = "Hernandez")
    @NotBlank
    private String mLastName;

    @Schema(description = "Vet date of birth", example = "1998-12-25")
    private LocalDate birthDate;

    @Schema(description = "Vet cellPhone", example = "+528332587821")
    private String cellPhone;

    @Schema(description = "Vet email address", example = "albertoHerdJua@gmail.com")
    private String email;

    @Schema(description = "Vet specialty", example = "odontologia")
    private String specialty;

    @Schema(description = "Vet's entry time", example = "09:25")
    private LocalTime entryTime;

    @Schema(description = "Vet's exit time", example = "18:15")
    private LocalTime exitTime;
}