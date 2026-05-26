package com.example.autodeploy_tracker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<?> handleNotFound(
            ResourceNotFoundException e) {

        return ResponseEntity.status(404)
                .body(Map.of(
                        "error",
                        e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)

    public ResponseEntity<?> handleBadState(
            IllegalStateException e) {

        return ResponseEntity.status(400)
                .body(Map.of(
                        "error",
                        e.getMessage()));
    }
}