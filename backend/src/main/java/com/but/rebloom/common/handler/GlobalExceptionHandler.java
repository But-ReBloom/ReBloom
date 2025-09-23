package com.but.rebloom.common.handler;

import com.but.rebloom.common.exception.AlreadyUsingIdException;
import com.but.rebloom.common.exception.TokenExpiredException;
import com.but.rebloom.common.exception.UserNotFoundException;
import com.but.rebloom.common.exception.WrongVerifiedCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyUsingIdException.class)
    public ResponseEntity<Object> handleAlreadyUsing(AlreadyUsingIdException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "success", false,
                        "error_name", "AlreadyUsingIdException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgs(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "success", false,
                        "error_name", "IllegalArgumentException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "success", false,
                        "error_name", "UserNotFoundException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpired(TokenExpiredException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "success", false,
                        "error_name", "TokenExpiredException",
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(WrongVerifiedCodeException.class)
    public ResponseEntity<Object> handleWrongCode(WrongVerifiedCodeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "success", false,
                        "error_name", "WrongVerifiedCodeException",
                        "message", e.getMessage()
                ));
    }
}
