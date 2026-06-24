package com.aula.oop.app.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceExceptionHandler {

    //livro n encontrado https://http.cat/status/404

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> livroNaoEncontrado(LivroNaoEncontradoException e, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Livro não encontrado");
        error.put("message", e.getMessage());
        error.put("path", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // código duplicado(conflito) https://http.cat/status/409

    @ExceptionHandler(CodigoDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> codigoDuplicado(CodigoDuplicadoException e, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", HttpStatus.CONFLICT.value());
        error.put("error", "Conflito de dados, esse código já existe.");
        error.put("message", e.getMessage());
        error.put("path", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    //erros de validação (@NotBlank, @Positive, etc) https://http.cat/status/422
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.put("error", "Erro de validação nos campos");

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.put("errors", fieldErrors);
        error.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}