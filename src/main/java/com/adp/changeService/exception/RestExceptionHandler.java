package com.adp.changeService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InsufficientChangeException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientChange(InsufficientChangeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Insufficient Change", Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
