package org.bedu.java.backend.veterinaria.dto.vet;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VetDTO {

    @Schema(description = "ID vet", example = "100")
    private Long id;

    @Schema(description = "Vet name", example = "Alberto")
    private String name;

    @Schema(description = "Vet surname", example = "Juarez")
    private String surname;

    @Schema(description = "Vet maternal surname", example = "Hernandez")
    private String maternalSurname;

    @Schema(description = "Vet date of birth", example = "1998-12-25")
    private LocalDate birthdate;

    @Schema(description = "Vet cellphone", example = "+528332587821")
    private String cellphone;

    @Schema(description = "Vet email address", example = "albertoHerdJua@gmail.com")
    private String email;

    @Schema(description = "Vet specialty", example = "Dentistry")
    private String specialty;

    @Schema(description = "Vet's entry time", example = "09:25")
    private LocalTime entryTime;

    @Schema(description = "Vet's exit time", example = "18:15")
    private LocalTime exitTime;

}