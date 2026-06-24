package com.aula.oop.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErroHandler {

    //esses erros globais aqui eu peguei com o chat pois nao tava ligado em como fazer

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<String> tratarLivroNaoEncontrado(
            LivroNaoEncontradoException ex){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(LivroExistenteComEsseCodigo.class)
    public ResponseEntity<String> tratarCodigoDuplicado(
            LivroExistenteComEsseCodigo ex){

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> tratarValidacoes(
            MethodArgumentNotValidException ex){

        Map<String,String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(erro ->
                        erros.put(
                                erro.getField(),
                                erro.getDefaultMessage()));

        return ResponseEntity.badRequest().body(erros);
    }
}

