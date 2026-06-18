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
    public ResponseEntity<Map<String, String>> tratarLivroNaoEncontrado(LivroNaoEncontradoException erro) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", erro.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(CodigoJaExisteException.class)
    public ResponseEntity<Map<String, String>> tratarCodigoDuplicado(CodigoJaExisteException erro) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", erro.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacoes(MethodArgumentNotValidException erro) {
        Map<String, String> resposta = new HashMap<>();

        erro.getBindingResult().getFieldErrors().forEach(campo -> {
            resposta.put(campo.getField(), campo.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}