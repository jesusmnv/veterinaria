package org.bedu.java.backend.veterinaria.exception;

public class FacturaNotFoundException extends RuntimeException {
    public FacturaNotFoundException(Long facturaId) {
        super("ERR_DATA_NOT_FOUND", "No se encontró la factura especificada", facturaId);
    }
}