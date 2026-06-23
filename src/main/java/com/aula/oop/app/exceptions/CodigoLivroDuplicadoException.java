package com.aula.oop.app.exceptions;

public class CodigoLivroDuplicadoException extends RuntimeException {

    public CodigoLivroDuplicadoException(String codigo) {
        super("Já existe um livro cadastrado com o código: " + codigo);
    }
}