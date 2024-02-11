package org.bedu.java.backend.veterinaria.dto.owner;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    @Schema(description = "Owner id", example = "23")
    private Long id;

    @Schema(description = "Nombre del propietario", example = "Miguel")
    private String name;

    @Schema(description = "Paternal last name", example = "Ramírez")
    private String pLastName;

    @Schema(description = "Maternal last name", example = "Tenorio")
    private String mLastName;

    @Schema(description = "Address", example = "Avenida 12 #34, Angelópolis, Puebla")
    private String address;

    @Schema(description = "Cell phone", example = "+52 651 239 6312")
    private String cellPhone;

    @Schema(description = "Owner's email", example = "tenorio.miguel@gmail.com")
    private String email;

    @Schema(description = "Owner's date of birth", example = "1998-08-23")
    private LocalDate birthDate;

    @Schema(description = "Owner's occupation", example = "Empleado")
    private String occupation;

}
