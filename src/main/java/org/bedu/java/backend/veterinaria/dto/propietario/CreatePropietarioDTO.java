package org.bedu.java.backend.veterinaria.dto.propietario;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePropietarioDTO {

    @Schema(description = "Nombre del propietario", example = "Miguel")
    @NotBlank(message = "El nombre del propietario es obligatorio")
    @Length(min = 1, max = 70, message = "El nombre no puede exceder de 70 caracteres")
    private String nombre;

    @Schema(description = "Apellido paterno", example = "Ramírez")
    @NotBlank(message = "El apellido paterno es obligatorio")
    @Length(min = 1, max = 70, message = "El apellido paterno no puede exceder de 70 caracteres")
    private String apellidoPaterno;

    @Schema(description = "Apellido materno", example = "Tenorio")
    @NotBlank(message = "El apellido materno es obligatorio")
    @Length(min = 1, max = 70, message = "El apellido materno no puede exceder de 70 caracteres")
    private String apellidoMaterno;

    @Schema(description = "Dirección", example = "Avenida 12 #34, Angelópolis, Puebla")
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Schema(description = "Número de teléfono móvil", example = "+52 651 239 6312")
    @NotBlank(message = "El teléfono de contacto es obligatorio")
    @Length(min = 1, max = 15, message = "El teléfono no puede exceder de 15 caracteres")
    private String celular;

    @Schema(description = "Email del propietario", example = "tenorio.miguel@gmail.com")
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Length(min = 1, max = 150, message = "El email no puede exceder de 150 caracteres")
    private String correo;

    @Schema(description = "Fecha de nacimiento del propietario", example = "1998-08-23")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @Schema(description = "Ocupación del propietario", example = "Empleado")
    @Column(nullable = false, length = 100)
    @Length(min = 1, max = 60, message = "La ocupación no puede exceder de 60 caracteres")
    private String ocupacion;

}
