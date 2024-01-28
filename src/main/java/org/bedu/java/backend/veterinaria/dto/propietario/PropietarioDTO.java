package org.bedu.java.backend.veterinaria.dto.propietario;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropietarioDTO {

    @Schema(description = "Identificador del propietario", example = "23")
    private Long id;

    @Schema(description = "Nombre del propietario", example = "Miguel")
    private String nombre;

    @Schema(description = "Apellido paterno", example = "Ramírez")
    private String apellidoPaterno;

    @Schema(description = "Apellido materno", example = "Tenorio")
    private String apellidoMaterno;

    @Schema(description = "Dirección", example = "Avenida 12 #34, Angelópolis, Puebla")
    private String direccion;

    @Schema(description = "Número de teléfono móvil", example = "+52 651 239 6312")
    private String celular;

    @Schema(description = "Email del propietario", example = "tenorio.miguel@gmail.com")
    private String correo;

    @Schema(description = "Fecha de nacimiento del propietario", example = "1998-08-23")
    private Date fechaNacimiento;

    @Schema(description = "Ocupación del propietario", example = "Empleado")
    private String ocupacion;

}
