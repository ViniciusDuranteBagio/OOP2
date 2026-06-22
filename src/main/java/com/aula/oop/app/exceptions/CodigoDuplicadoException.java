package com.aula.oop.app.exceptions;

public class CodigoDuplicadoException extends RuntimeException {

    public CodigoDuplicadoException(String mensagem) {
        super(mensagem);
    }

}