package org.bedu.java.backend.veterinaria.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RuntimeExceptionTest {
    @Test
    void testConstructorAndGetters() {
        String code = "ERROR_CODE";
        String message = "Error message";
        Object details = new Object();

        RuntimeException exception = new RuntimeException(code, message, details);

        assertEquals(message, exception.getMessage());
        assertEquals(code, exception.getCode());
        assertEquals(details, exception.getDetails());
    }
}
