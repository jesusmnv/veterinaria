package org.bedu.java.backend.veterinaria.exception;

public class MedicationNotFoundException extends RuntimeException {
    public MedicationNotFoundException(Long medicationId) {
        super("ERR_DATA_NOT_FOUND", "The specified medication was not found", medicationId);
    }
}