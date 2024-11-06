package com.example.medicaltermsdictionary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.medicaltermsdictionary.constant.ErrorConstants;

/**
 * Global exception handler for the Medical Terms Dictionary application.
 * Provides centralized handling of exceptions thrown by the application,
 * mapping each exception type to an appropriate HTTP response.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link TermNotFoundException} exceptions. This exception is
     * thrown when a requested medical term is not found in the database.
     *
     * @param ex the exception thrown when a term is not found
     * @return a {@link ResponseEntity} containing the exception message and an HTTP
     *         404 status
     */
    @ExceptionHandler(TermNotFoundException.class)
    public ResponseEntity<String> handleTermNotFoundException(TermNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link DuplicateFavoriteException} exceptions. This exception is
     * thrown when an attempt is made to mark a medical term as a favorite that is
     * already marked as a favorite.
     *
     * @param ex the exception thrown when attempting to duplicate a favorite entry
     * @return a {@link ResponseEntity} containing the exception message and an HTTP
     *         400 status
     */
    @ExceptionHandler(DuplicateFavoriteException.class)
    public ResponseEntity<String> handleDuplicateFavoriteException(DuplicateFavoriteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles any other uncaught exceptions. This generic exception handler
     * provides a fallback for all unexpected errors that may occur within the
     * application.
     *
     * @param ex the unexpected exception thrown
     * @return a {@link ResponseEntity} containing a general error message defined
     *         in {@link ErrorConstants}
     *         and an HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ErrorConstants.GENERAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
