package org.bedu.java.backend.veterinaria.exception;

public class ConsultationNotFoundException extends RuntimeException {
    public ConsultationNotFoundException(Long consultationId) {
        super("ERR_DATA_NOT_FOUND", "Specified consultation not found", consultationId);
    }
}