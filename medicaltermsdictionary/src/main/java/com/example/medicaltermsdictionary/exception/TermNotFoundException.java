package com.example.medicaltermsdictionary.exception;

public class TermNotFoundException extends RuntimeException {
    public TermNotFoundException(String message) {
        super(message);
    }
}
