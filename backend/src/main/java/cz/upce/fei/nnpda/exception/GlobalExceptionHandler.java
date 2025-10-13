package cz.upce.fei.nnpda.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        // Unique constrain was violated
        if (ex instanceof DataIntegrityViolationException)
            return createResponse("Jedna, nebo více položek není unikatní.", HttpStatus.CONFLICT);

        // Desired content was not found
        if (ex instanceof EntityNotFoundException)
            return createResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

        // Invalid access
        if (ex instanceof AccessDeniedException)
            return createResponse(ex.getMessage(), HttpStatus.FORBIDDEN);

        // JWT Expired
        if (ex instanceof ExpiredJwtException)
            return createResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);

        return createResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return createResponse("Jedna, nebo více položek není unikatní.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        return createResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException ex) {
        return createResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Map<String, String>> createResponse(String message, HttpStatus status) {
        Map<String, String> body = new HashMap<>();
        body.put("error", message);
        return new ResponseEntity<>(body, status);
    }

    /*
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleDeserializationException(HttpMessageNotReadableException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid input: Please check your data types (e.g. number must be numeric).");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
     */
}

