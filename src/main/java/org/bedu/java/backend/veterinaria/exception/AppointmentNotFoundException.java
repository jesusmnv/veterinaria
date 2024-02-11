package org.bedu.java.backend.veterinaria.exception;

public class AppointmentNotFoundException extends RuntimeException{
    public AppointmentNotFoundException(Long appointmentId) {
        super("ERR_DATA_NOT_FOUND", "The specified appointment was not found", appointmentId);
    }
}