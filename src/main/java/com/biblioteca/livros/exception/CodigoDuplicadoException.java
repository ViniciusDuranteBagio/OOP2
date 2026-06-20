package com.biblioteca.livros.exception;

public class CodigoDuplicadoException extends RuntimeException {

    public CodigoDuplicadoException(String codigo) {
        super("Já existe um livro cadastrado com o código: " + codigo);
    }
}
