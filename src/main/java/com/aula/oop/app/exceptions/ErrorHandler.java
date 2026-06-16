package com.aula.oop.app.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity resposta400(BadRequestException ex) {
        Map body = Map.of(
                "status", 404,
                "mensagem", ex.getMessage()
        );

        return ResponseEntity.status(400).body(body);
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> respostaValidacao(MethodArgumentNotValidException ex) {
            String mensagem = ex.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(Map.of("status", 400, "mensagem", mensagem));
        }
    }