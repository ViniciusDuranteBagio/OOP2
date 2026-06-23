package com.aula.oop.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidacao(MethodArgumentNotValidException ex) {
        StringBuilder mensagens = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                mensagens.append(erro.getField())
                        .append(": ")
                        .append(erro.getDefaultMessage())
                        .append("; ")
        );
        return montarCorpo(HttpStatus.BAD_REQUEST, mensagens.toString());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleJsonInvalido(HttpMessageNotReadableException ex) {
        return montarCorpo(HttpStatus.BAD_REQUEST,
                "Body não está em JSON");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleTipoInvalidoNaUrl(MethodArgumentTypeMismatchException ex) {
        String mensagem = String.format(
                "O parâmetro '%s' está com uma tipagem incorreta",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido",
                ex.getValue()
        );
        return montarCorpo(HttpStatus.BAD_REQUEST, mensagem);
    }

    private Map<String, Object> montarCorpo(HttpStatus status, String mensagem) {
        Map<String, Object> corpo = new HashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", status.value());
        corpo.put("erro", status.getReasonPhrase());
        corpo.put("mensagem", mensagem);
        return corpo;
    }

    @ExceptionHandler(CodigoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleCodigoDuplicado(CodigoDuplicadoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return erro;
    }

}