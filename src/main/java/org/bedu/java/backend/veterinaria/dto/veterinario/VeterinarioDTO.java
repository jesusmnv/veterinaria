package org.bedu.java.backend.veterinaria.dto.veterinario;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VeterinarioDTO {

    @Schema(description = "Identificador del vet", example = "100")
    private Long id;

    @Schema(description = "Nombre del vet", example = "Alberto")
    private String nombre;

    @Schema(description = "Apellido paterno del vet", example = "Juarez")
    private String apellidoPaterno;

    @Schema(description = "Apellido materno del vet", example = "Hernandez")
    private String apellidoMaterno;

    @Schema(description = "Fecha de nacimiento del vet", example = "1998-12-25")
    private LocalDate fechaNacimiento;

    @Schema(description = "Numero de celular del vet", example = "+528332587821")
    private String celular;

    @Schema(description = "Correo electronico del vet", example = "albertoHerdJua@gmail.com")
    private String correo;

    @Schema(description = "Especialidad del vet", example = "odontologia")
    private String especialidad;

    @Schema(description = "Hora de entrada del vet", example = "09:25")
    private LocalTime horaEntrada;

    @Schema(description = "Hora de salida del vet", example = "18:15")
    private LocalTime horaSalida;

}