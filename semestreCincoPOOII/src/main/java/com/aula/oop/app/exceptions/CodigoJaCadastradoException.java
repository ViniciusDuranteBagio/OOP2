package com.aula.oop.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CodigoJaCadastradoException extends RuntimeException {

    public CodigoJaCadastradoException(String codigo) {
        super("Já existe um cadastro correspondente ao código: " + codigo);
    }
}