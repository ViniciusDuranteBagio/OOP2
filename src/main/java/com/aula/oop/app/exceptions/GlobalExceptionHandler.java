package com.aula.oop.app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNotFeoundException.class)
    public ResponseEntity<?> handleNotFound(LivroNotFeoundException ex) {
        return ResponseEntity.status(404)
                .body(Map.of("status", 404, "mensagem", ex.getMessage()));
    }

    @ExceptionHandler(CodigoJaCadastradoException.class)
    public ResponseEntity<?> handleCodigoDuplicado(CodigoJaCadastradoException ex) {
        return ResponseEntity.status(409)
                .body(Map.of("status", 409, "mensagem", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }
        return ResponseEntity.status(400)
                .body(Map.of("status", 400, "erros", erros));
    }

}
