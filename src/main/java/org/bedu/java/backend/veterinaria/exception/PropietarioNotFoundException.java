package org.bedu.java.backend.veterinaria.exception;

public class PropietarioNotFoundException extends RuntimeException {
    public PropietarioNotFoundException(Long propietarioId) {
        super("ERR_DATA_NOT_FOUND", "No se encontró el propietario especificado", propietarioId);
    }
}