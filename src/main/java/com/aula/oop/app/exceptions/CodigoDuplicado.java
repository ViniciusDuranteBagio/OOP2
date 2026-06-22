package com.aula.oop.app.exceptions;

public class CodigoDuplicado extends RuntimeException {

    public CodigoDuplicado(String mensagem) {
        super(mensagem);
    }
}
