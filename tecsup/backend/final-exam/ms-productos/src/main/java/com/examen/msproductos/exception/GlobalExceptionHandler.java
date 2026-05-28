package com.examen.msproductos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(
            RecursoNoEncontradoException ex
    ) {

        Map<String, Object> error = new HashMap<>();

        error.put("mensaje", ex.getMessage());
        error.put("fecha", LocalDateTime.now());
        error.put("estado", 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(
            MethodArgumentNotValidException ex
    ) {

        Map<String, Object> error = new HashMap<>();

        error.put(
                "mensaje",
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage()
        );

        error.put("fecha", LocalDateTime.now());
        error.put("estado", 400);

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarGeneral(
            Exception ex
    ) {

        Map<String, Object> error = new HashMap<>();

        error.put("mensaje", "Error interno del servidor");
        error.put("detalle", ex.getMessage());
        error.put("fecha", LocalDateTime.now());
        error.put("estado", 500);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}