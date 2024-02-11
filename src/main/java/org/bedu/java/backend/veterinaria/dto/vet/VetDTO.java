package org.bedu.java.backend.veterinaria.dto.vet;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VetDTO {

    @Schema(description = "ID vet", example = "100")
    private Long id;

    @Schema(description = "Vet name", example = "Alberto")
    private String name;

    @Schema(description = "Vet paternal last name", example = "Juarez")
    private String pLastName;

    @Schema(description = "Vet maternal last name", example = "Hernandez")
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