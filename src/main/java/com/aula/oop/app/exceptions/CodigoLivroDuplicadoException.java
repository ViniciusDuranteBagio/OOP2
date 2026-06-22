package com.aula.oop.app.exceptions;

public class CodigoLivroDuplicadoException extends RuntimeException {

    public CodigoLivroDuplicadoException(String codigo) {
        super("Ja existe um livro cadastrado com o codigo " + codigo + ".");
    }
}
