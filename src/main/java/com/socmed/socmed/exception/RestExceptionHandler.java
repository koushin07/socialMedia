package com.socmed.socmed.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PasswordConfirmationException.class)
    @ResponseStatus(code = FORBIDDEN)
    public String handlePasswordConfirmationException(PasswordConfirmationException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(EmailExistException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public String handleEmailExistException(EmailExistException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(UniqueException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public String handleUniqueException(UniqueException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(TokenBlacklistedException.class)
    @ResponseStatus(code = UNAUTHORIZED)
    public String handleTokenBlacklistedException(TokenBlacklistedException ex){
        return ex.getMessage();
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, headers, status);
    }
}

