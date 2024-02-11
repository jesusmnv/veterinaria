package org.bedu.java.backend.veterinaria.exception;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(Long ownerId) {
        super("ERR_DATA_NOT_FOUND", "Owner not found", ownerId);
    }
}