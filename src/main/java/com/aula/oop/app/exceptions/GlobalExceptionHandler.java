package com.aula.oop.app.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(LivroNaoEncontradoException.class)
        public ResponseEntity<String> tratarLivroNaoEncontrado(
                LivroNaoEncontradoException ex) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }

        @ExceptionHandler(CodigoDuplicadoException.class)
        public ResponseEntity<String> tratarCodigoDuplicado(
                CodigoDuplicadoException ex) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> tratarValidacoes(
                MethodArgumentNotValidException ex) {

            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(erros);
        }
    }

