package com.aula.oop.app.exceptions;

public class CodigoDuplicadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CodigoDuplicadoException() {
        super("Código duplicado!");
    }

    public CodigoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}