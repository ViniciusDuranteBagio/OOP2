package com.aula.oop.app.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> respostaValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(Map.of("status", 400, "mensagem", mensagem));
    }
}
