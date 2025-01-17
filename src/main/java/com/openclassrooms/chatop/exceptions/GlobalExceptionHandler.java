package com.openclassrooms.chatop.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class

    })
    public ResponseEntity<String> handleValidationExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{}");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleValidationExceptions(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{ \"message\": \"error\" }");
    }


    /**
     * Handles ConstraintViolationException, which is thrown when simple parameters
     * (@RequestParam, @PathVariable) violate validation constraints.
     *
     * @param exception ConstraintViolationException
     * @return ResponseEntity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(ConstraintViolationException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(error -> String.format("Field '%s': %s", error.getPropertyPath().toString(), error.getMessage()))
                .collect(Collectors.toList());

        problemDetail.setProperty("errors", errors);
        return ResponseEntity.badRequest().body(problemDetail);
    }

}