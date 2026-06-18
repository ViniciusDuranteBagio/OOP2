package com.aula.oop.app.exceptions;

public class CodigoJaExisteException extends RuntimeException {

    public CodigoJaExisteException(String mensagem) {
        super(mensagem);
    }
}