package com.aula.oop.app.livros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now().toString());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "campos invalidos");
        resposta.put("campos", erros);

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(LivroNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleLivroNotFound(LivroNotFoundException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now().toString());
        resposta.put("status", HttpStatus.NOT_FOUND.value());
        resposta.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(CodigoDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> handleCodigoDuplicado(CodigoDuplicadoException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now().toString());
        resposta.put("status", HttpStatus.CONFLICT.value());
        resposta.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenerico(Exception ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now().toString());
        resposta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        resposta.put("erro", "erro interno, tente novamente");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}