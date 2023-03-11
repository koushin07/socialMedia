package com.socmed.socmed.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(ResourceNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, NOT_FOUND);
    }
}
