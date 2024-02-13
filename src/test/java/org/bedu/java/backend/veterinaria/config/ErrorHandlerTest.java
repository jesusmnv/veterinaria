package org.bedu.java.backend.veterinaria.config;

import org.bedu.java.backend.veterinaria.dto.ErrorDTO;
import org.bedu.java.backend.veterinaria.exception.RuntimeException;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ErrorHandlerTest {

    @Test
    void validationErrorTest() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

        BindingResult bindingResult = mock(BindingResult.class);
        List<FieldError> fieldErrors = Arrays.asList(new FieldError("objectName", "fieldName", "Error message"));
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDTO result = errorHandler.validationError(ex);

        assertEquals("ERR_VALID", result.getCode());
        assertEquals("There was an error validating the input data", result.getMessage());
        assertEquals(Arrays.asList("Error message"), result.getDetails());
    }

    @Test
    void applicationErrorTest() {
        RuntimeException ex = new RuntimeException("ERROR_CODE", "Error message", "Details");
        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDTO result = errorHandler.applicationError(ex);

        assertEquals("ERROR_CODE", result.getCode());
        assertEquals("Error message", result.getMessage());
        assertEquals("Details", result.getDetails());
    }

    @Test
    void unknownErrorTest() {
        Exception ex = new Exception("Error message");
        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDTO result = errorHandler.unknownError(ex);

        assertEquals("ERR_UNKNOWN", result.getCode());
        assertEquals("An unexpected error occurred", result.getMessage());
        assertEquals(null, result.getDetails());
    }
}

