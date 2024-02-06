package org.bedu.java.backend.veterinaria.exception;

public class CitaNotFoundException extends RuntimeException{
    public CitaNotFoundException(Long citaId) {
        super("ERR_DATA_NOT_FOUND", "No se encontro la cita especificada", citaId);
    }
}