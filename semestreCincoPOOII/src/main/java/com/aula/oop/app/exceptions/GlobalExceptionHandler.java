package com.aula.oop.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleLivroNaoEncontradoException(LivroNaoEncontradoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro); // aq status 404
    }

    @ExceptionHandler(CodigoJaCadastradoException.class)
    public ResponseEntity<Map<String, String>> handleCodigoDuplicadoException(CodigoJaCadastradoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro); // aqui 409, dados duplicados
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                erros.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros); // 400 bad request
    }
}