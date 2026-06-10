package com.aula.oop.app.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity resposta400(BadRequestException ex) {
        Map body = Map.of(
                "status", 404,
                "mensagem", ex.getMessage()
        );

        return ResponseEntity.status(400).body(body);
    }
}
