package com.YK5maurice.Inventory_management.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Gestion de l'exception "ResourceNotFoundException"
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Gestion de l'exception "ValidationException"
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String>handleValidationException(ValidationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //Gestion de l'exception "ResourceNoConten"
    @ExceptionHandler(ResourceNoContent.class)
    public ResponseEntity<String>handleResourceNoConten(ResourceNoContent ex){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }

    // Gestion générique des autres exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Une erreur inattendue est survenue : " + ex.getMessage());
    }
}
