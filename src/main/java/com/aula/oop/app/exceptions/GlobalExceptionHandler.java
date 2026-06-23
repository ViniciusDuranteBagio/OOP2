package com.aula.oop.app.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNotFound.class)
    public ResponseEntity<ErroResponse> handleNotFound(LivroNotFound ex) {
        ErroResponse erro = new ErroResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(CodigoDuplicado.class)
    public ResponseEntity<ErroResponse> handleCodigoDuplicado(CodigoDuplicado ex) {
        ErroResponse erro = new ErroResponse(409, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidation(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErroResponse erro = new ErroResponse(400, mensagem);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
