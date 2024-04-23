package com.adp.changeService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ChangeException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientChange(ChangeException ex) {
        return new ResponseEntity<>(new ErrorResponse("Insufficient Change", Arrays.asList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BillException.class)
    public ResponseEntity<ErrorResponse> handleBillValueException(BillException ex) {
        return new ResponseEntity<>(new ErrorResponse("Invalid bill value", Arrays.asList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoinException.class)
    public ResponseEntity<ErrorResponse> handleCoinException(CoinException ex) {
        return new ResponseEntity<>(new ErrorResponse("Invalid coin amount", Arrays.asList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }
}
