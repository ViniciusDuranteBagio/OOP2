package com.aula.oop.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException(Long id) {
        super("Não encontramos cadastro correspondente ao id: " + id);
    }
}