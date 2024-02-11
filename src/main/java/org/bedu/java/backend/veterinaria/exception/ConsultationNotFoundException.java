package org.bedu.java.backend.veterinaria.exception;

public class ConsultaNotFoundException extends RuntimeException {
    public ConsultaNotFoundException(Long consultaId) {
        super("ERR_DATA_NOT_FOUND", "No se encontró la consulta especificada", consultaId);
    }
}