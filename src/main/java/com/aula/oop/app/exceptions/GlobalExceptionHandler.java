package com.aula.oop.app.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleLivroNaoEncontrado(LivroNaoEncontradoException ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", LocalDateTime.now().toString());
        erro.put("status", HttpStatus.NOT_FOUND.value());
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(CodigoJaCadastradoException.class)
    public ResponseEntity<Map<String, Object>> handleCodigoJaCadastrado(CodigoJaCadastradoException ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", LocalDateTime.now().toString());
        erro.put("status", HttpStatus.CONFLICT.value());
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, Object> erro = new HashMap<>();
        Map<String, String> campos = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            campos.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        erro.put("timestamp", LocalDateTime.now().toString());
        erro.put("status", HttpStatus.BAD_REQUEST.value());
        erro.put("mensagem", "Erro de validação nos campos");
        erro.put("campos", campos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
