package org.bedu.java.backend.veterinaria.dto.owner;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOwnerDTO {

    @Schema(description = "Owner name", example = "Miguel")
    @NotBlank(message = "Owner name is mandatory")
    @Length(min = 1, max = 70, message = "Owner's name must not exceed 70 chars")
    private String name;

    @Schema(description = "Paternal last name", example = "Ramírez")
    @NotBlank(message = "Paternal last name is mandatory")
    @Length(min = 1, max = 70, message = "Owner's paternal last name must not exceed 70 chars")
    private String pLastName;

    @Schema(description = "Maternal last name", example = "Tenorio")
    @NotBlank(message = "Maternal last name is mandatory")
    @Length(min = 1, max = 70, message = "Owner's maternal last name name must not exceed 70 chars")
    private String mLastName;

    @Schema(description = "Address", example = "Avenida 12 #34, Angelópolis, Puebla")
    @NotBlank(message = "Owner address is mandatory")
    private String address;

    @Schema(description = "Cell phone", example = "+52 651 239 6312")
    @NotBlank(message = "Cell phone is mandatory")
    @Length(min = 1, max = 15, message = "Owner's cell phone must not exceed 15 chars")
    private String cellPhone;

    @Schema(description = "Owner's email", example = "tenorio.miguel@gmail.com")
    @NotBlank(message = "Owner's email is mandatory")
    @Email(message = "The owner's email must be in a valid format")
    @Length(min = 1, max = 150, message = "The email must not exceed 150 chars")
    private String email;

    @Schema(description = "Owner's date of birth", example = "1998-08-23")
    @NotNull(message = "Date of birth is mandatory")
    private LocalDate birthDate;

    @Schema(description = "Owner's occupation", example = "Empleado")
    @Column(nullable = false, length = 100)
    @Length(min = 1, max = 60, message = "The occupation cannot exceed 60 chars")
    private String occupation;

}
