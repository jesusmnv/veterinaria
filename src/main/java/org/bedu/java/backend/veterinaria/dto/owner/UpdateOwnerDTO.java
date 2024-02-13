package org.bedu.java.backend.veterinaria.dto.owner;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateOwnerDTO {
    @Schema(description = "Owner's name", example = "Miguel")
    private String nameU;

    @Schema(description = "Paternal last name", example = "Ramírez")
    private String surnameU;

    @Schema(description = "Maternal last name", example = "Tenorio")
    private String maternalSurnameU;

    @Schema(description = "Address", example = "Avenida 12 #34, Angelópolis, Puebla")
    private String addressU;

    @Schema(description = "Cellphone", example = "+52 651 239 6312")
    private String cellphoneU;

    @Schema(description = "Owner's email", example = "tenorio.miguel@gmail.com")
    private String emailU;

    @Schema(description = "Owner's date of birth", example = "1998-08-23")
    private LocalDate birthdateU;

    @Schema(description = "Owner's occupation", example = "Empleado")
    private String occupationU;

}
