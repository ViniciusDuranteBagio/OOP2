package com.biblioteca.livros.exception;

public class CodigoDuplicadoException extends RuntimeException {

    public CodigoDuplicadoException(String codigo) {
        super("ja existe um livro com esse codigo: " + codigo);
    }
}
