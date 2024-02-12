package org.bedu.java.backend.veterinaria.dto.vet;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateVetDTO {

    @Schema(description = "Vet name", example = "Alberto")
    @NotBlank(message = "The vet name is mandatory")
    @Length(min = 1, max = 100, message = "Vet's name must not exceed 100 chars")
    private String name;

    @Schema(description = "Paternal last name", example = "Juarez")
    @NotBlank(message = "The vet paternal last name is mandatory")
    @Length(min = 1, max = 100, message = "The paternal last name can't exceed 100 characteres")
    private String surname;

    @Schema(description = "Maternal last name", example = "Hernandez")
    @NotBlank(message = "The vet maternal last name is mandatory")
    @Length(min = 1, max = 100, message = "The maternal last name can't exceed 100 characteres")
    private String maternalSurname;

    @Schema(description = "Date of Birth", example = "1998-12-25")
    @NotNull(message = "The birthdate cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Schema(description = "Cellphone", example = "+528331593654")
    @NotBlank(message = "The vet cellphone is mandatory")
    @Length(min = 1, max = 100, message = "The cellphone can't exceed 13 characteres")
    private String cellphone;

    @Schema(description = "Email address", example = "albertojuHrd@gmail.com")
    @NotBlank(message = "The vet email address is mandatory")
    @Length(min = 1, max = 100, message = "The email address can't exceed 100 characteres")
    private String email;

    @Schema(description = "Specialty", example = "Odontología")
    @NotBlank(message = "The vet specialty is mandatory")
    @Length(min = 1, max = 100, message = "The specialty can't exceed 100 characters")
    private String specialty;

    @Schema(description = "Vet's entry time", example = "09:25")
    @NotNull(message = "Entry time cannot be null")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime entryTime;

    @Schema(description = "Vet's exit time", example = "18:15")
    @NotNull(message = "Exit time cannot be null")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime exitTime;

}