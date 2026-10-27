package com.tecsup.biblioteca.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

// Atrapa las excepciones de arriba y devuelve el código HTTP correcto
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<Map<String, String>> reglaNegocio(ReglaNegocioException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));  // 400
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> noEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));   // 404
    }
}