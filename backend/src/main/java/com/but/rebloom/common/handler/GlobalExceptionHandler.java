package com.but.rebloom.common.handler;

import com.but.rebloom.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.swing.text.html.parser.Entity;
import java.lang.IllegalArgumentException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyUsingException.class)
    public ResponseEntity<Object> handleAlreadyUsing(AlreadyUsingException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)    // 409
                .body(Map.of(
                        "success", false,
                        "error_name", "AlreadyUsingIdException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgs(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(Map.of(
                        "success", false,
                        "error_name", "IllegalArgumentException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)   // 404
                .body(Map.of(
                        "success", false,
                        "error_name", "NotFoundException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthentication(AuthenticationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)     // 400
                .body(Map.of(
                        "success", false,
                        "error_name", "AuthenticationException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(EntityException.class)
    public ResponseEntity<Object> handleEntity(EntityException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)   // 500
                .body(Map.of(
                        "success", false,
                        "error_name", "EntityException",
                        "message", e.getMessage()
                ));
    }
}
