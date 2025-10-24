package com.but.rebloom.common.handler;

import com.but.rebloom.auth.exception.TokenExpiredException;
import com.but.rebloom.auth.exception.UserNotFoundException;
import com.but.rebloom.auth.exception.WrongVerifiedCodeException;
import com.but.rebloom.common.exception.*;
import com.but.rebloom.hobby.exception.ActivityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)   // 404
                .body(Map.of(
                        "success", false,
                        "error_name", "UserNotFoundException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(ActivityNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ActivityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)   // 404
                .body(Map.of(
                        "success", false,
                        "error_name", "ActivityNotFoundException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpired(TokenExpiredException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)    // 401
                .body(Map.of(
                        "success", false,
                        "error_name", "TokenExpiredException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(WrongVerifiedCodeException.class)
    public ResponseEntity<Object> handleWrongCode(WrongVerifiedCodeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(Map.of(
                        "success", false,
                        "error_name", "WrongVerifiedCodeException",
                        "message", e.getMessage()
                ));
    }
}
