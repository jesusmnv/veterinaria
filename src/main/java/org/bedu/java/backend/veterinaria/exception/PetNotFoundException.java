package org.bedu.java.backend.veterinaria.exception;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(Long petId) {
        super("ERR_DATA_NOT_FOUND", "Specified pet not found", petId);
    }
}