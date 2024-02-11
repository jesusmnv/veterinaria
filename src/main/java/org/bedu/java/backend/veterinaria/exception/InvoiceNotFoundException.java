package org.bedu.java.backend.veterinaria.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(Long facturaId) {
        super("ERR_DATA_NOT_FOUND", "The specified invoice was not found", facturaId);
    }
}