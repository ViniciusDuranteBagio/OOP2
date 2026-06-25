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
public class TratadorGlobalDeExcecoes {

    @ExceptionHandler(com.aula.oop.app.exceptions.RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> tratarRecursoNaoEncontrado(com.aula.oop.app.exceptions.RecursoNaoEncontradoException ex) {
        return montarErro(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(com.aula.oop.app.exceptions.RegraDeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> tratarRegraDeNegocio(com.aula.oop.app.exceptions.RegraDeNegocioException ex) {
        return montarErro(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("timestamp", LocalDateTime.now());

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> erros.put(error.getField(), error.getDefaultMessage()));

        body.put("erros", erros);
        return body;
    }

    private Map<String, Object> montarErro(int status, String mensagem) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("mensagem", mensagem);
        body.put("timestamp", LocalDateTime.now());
        return body;
    }
}