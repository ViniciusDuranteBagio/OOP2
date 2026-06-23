package com.aula.oop.app.exceptions;

public class CodigoDuplicadoException extends RuntimeException {

    public CodigoDuplicadoException(String codigo) {
        super("Ja existe um livro cadastrado com o codigo '" + codigo + "'");
    }

}