package org.bedu.java.backend.veterinaria.exception;

public class VetNotFoundException extends RuntimeException{

    public VetNotFoundException(Long vetId){
        super("ERR_DATA_NOT_FOUND","Specified vet not found",vetId);
    }
    
}