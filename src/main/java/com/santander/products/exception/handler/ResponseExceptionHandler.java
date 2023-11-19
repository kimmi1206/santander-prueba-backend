package com.santander.products.exception.handler;

import com.santander.products.exception.ProductNotFoundException;
import com.santander.products.exception.ProductoAlreadyExistsException;
import com.santander.products.exception.ProductoNotDeletedException;
import com.santander.products.exception.ProductoNotSavedException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getConstraintViolations());

        LOGGER.error("ERROR: ConstraintViolationException --> Class, Method, Line Number: {}",
                Arrays.stream(ex.getStackTrace()).limit(5).toList());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(
            ProductNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Producto No Encontrado");

        LOGGER.error("ERROR: ProductNotFoundException --> Class, Method, Line Number: {}",
                Arrays.stream(ex.getStackTrace()).limit(5).toList());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductoAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductoAlreadyExistsException(
            ProductoAlreadyExistsException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Producto Ya Existe");

        LOGGER.error("ERROR: ProductoAlreadyExistsException --> Class, Method, Line Number: {}",
                Arrays.stream(ex.getStackTrace()).limit(5).toList());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductoNotSavedException.class)
    public ResponseEntity<Object> handleProductoNotSavedException(
            ProductoNotSavedException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Producto No Guardado");

        LOGGER.error("ERROR: ProductoNotSavedException --> Class, Method, Line Number: {}",
                Arrays.stream(ex.getStackTrace()).limit(5).toList());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductoNotDeletedException.class)
    public ResponseEntity<Object> handleProductoNotDeletedException(
            ProductoNotDeletedException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Producto No Eliminado");

        LOGGER.error("ERROR: ProductoNotDeletedException --> Class, Method, Line Number: {}",
                Arrays.stream(ex.getStackTrace()).limit(5).toList());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
            HttpStatusCode status, @NonNull WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = new ArrayList<>();
        try {
            errors = ex.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
        } catch (Exception exc) {
            LOGGER.error(exc.getMessage());
        }
        body.put("errors", errors);

        LOGGER.info("ERROR: MethodArgumentNotValidException --> {}", body);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
