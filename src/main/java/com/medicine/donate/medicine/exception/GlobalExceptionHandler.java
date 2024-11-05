package com.medicine.donate.medicine.exception;

import com.medicine.donate.medicine.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        bindingResult.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        GenericResponse<Map<String, String>> response = GenericResponse.<Map<String, String>>builder()
                .body(errors)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(new ArrayList<>(errors.values()))
                .build();

        return ResponseEntity.badRequest().body(response);
    }



    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<GenericResponse<String>> handleUnauthorizedException(UnauthorizedException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
                .body(null)
                .status(HttpStatus.UNAUTHORIZED.value())
                .errors(Collections.singletonList(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GenericResponse<String>> handleBadRequestException(BadRequestException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
                .body(null)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(Collections.singletonList(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GenericResponse<String>> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
                .body(null)
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .errors(Collections.singletonList("Method not allowed: " + ex.getMethod()))
                .build();

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleObjectNotFoundException(ObjectNotFoundException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
                .body(null)
                .status(HttpStatus.NOT_FOUND.value())
                .errors(Collections.singletonList("Object Not Found: " + ex.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<GenericResponse<String>> handleInternalServerErrorException(InternalServerErrorException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
                .body(null)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errors(Collections.singletonList("Internal Error: " + ex.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<GenericResponse<String>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
                .body(null)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errors(Collections.singletonList("Internal Error: " + ex.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<GenericResponse<String>> handleBusinessException(BusinessException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
                .body(null)
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .errors(Collections.singletonList(ex.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleNotFoundException(NoHandlerFoundException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
                .body(null)
                .status(HttpStatus.NOT_FOUND.value())
                .errors(Collections.singletonList("The requested resource was not found."))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }



}