package com.aula.oop.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidacao(MethodArgumentNotValidException ex) {
        StringBuilder mensagens = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(erro -> mensagens
                .append(erro.getField()).append(": ").append(erro.getDefaultMessage()).append("; "));

        Map<String, Object> corpo = new HashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", HttpStatus.BAD_REQUEST.value());
        corpo.put("erro", HttpStatus.BAD_REQUEST.getReasonPhrase());
        corpo.put("mensagem", mensagens.toString());
        return corpo;
    }
}