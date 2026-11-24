package org.example.spring_boot_user_management_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException exception) {

        return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateResourceException exception) {

        return buildErrorResponse(HttpStatus.CONFLICT, exception.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status: ", status.value());
        body.put("error: ", status.getReasonPhrase());
        body.put("message: ", message);
        body.put("timestamp: ", LocalDateTime.now());

        return ResponseEntity
                .status(status)
                .body(body);
    }
}
