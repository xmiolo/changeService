package com.adp.changeService.exception;

public class InsufficientChangeException extends RuntimeException {

    public InsufficientChangeException(String message) {
        super(message);
    }
}