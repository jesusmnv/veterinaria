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
public class CreateVetDTO {

    @Schema(description = "Vet name", example = "Alberto")
    @NotBlank(message = "The vet name is mandatory")
    @Length(min = 1, max = 100, message = "Vet's name must not exceed 100 chars")
    private String nameVet;

    @Schema(description = "Surname", example = "Juarez")
    @NotBlank(message = "The vet surname is mandatory")
    @Length(min = 1, max = 100, message = "The surname can't exceed 100 characteres")
    private String surnameVet;

    @Schema(description = "Maternal surname", example = "Hernandez")
    @NotBlank(message = "The vet maternal surname is mandatory")
    @Length(min = 1, max = 100, message = "The maternal surname can't exceed 100 characteres")
    private String maternalSurnameVet;

    @Schema(description = "Date of Birth", example = "1998-12-25")
    @NotNull(message = "The birthdate cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdateVet;

    @Schema(description = "Cellphone", example = "+528331593654")
    @NotBlank(message = "The vet cellphone is mandatory")
    @Length(min = 1, max = 100, message = "The cellphone can't exceed 13 characteres")
    private String cellphoneVet;

    @Schema(description = "Email address", example = "albertojuHrd@gmail.com")
    @NotBlank(message = "The vet email address is mandatory")
    @Length(min = 1, max = 100, message = "The email address can't exceed 100 characteres")
    private String emailVet;

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