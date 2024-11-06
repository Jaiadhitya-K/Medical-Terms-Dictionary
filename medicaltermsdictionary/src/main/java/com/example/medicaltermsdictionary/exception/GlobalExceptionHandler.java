package com.example.medicaltermsdictionary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.medicaltermsdictionary.constant.ErrorConstants;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TermNotFoundException.class)
    public ResponseEntity<String> handleTermNotFoundException(TermNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateFavoriteException.class)
    public ResponseEntity<String> handleDuplicateFavoriteException(DuplicateFavoriteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ErrorConstants.GENERAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}