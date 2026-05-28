package com.examen.ms_pedidos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PedidoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> manejarPedidoNoEncontrado(
            PedidoNotFoundException ex) {

        Map<String, Object> error = new HashMap<>();

        error.put("mensaje", ex.getMessage());
        error.put("fecha", LocalDateTime.now());

        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> manejarValidaciones(
            MethodArgumentNotValidException ex) {

        Map<String, Object> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        return errores;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> manejarErrorGeneral(
            Exception ex) {

        Map<String, Object> error = new HashMap<>();

        error.put("mensaje", "Error interno del servidor");
        error.put("detalle", ex.getMessage());
        error.put("fecha", LocalDateTime.now());

        return error;
    }
}