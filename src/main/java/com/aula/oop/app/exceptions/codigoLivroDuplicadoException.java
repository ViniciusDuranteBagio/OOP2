package com.aula.oop.app.exceptions;

public class codigoLivroDuplicadoException extends RuntimeException {

    public codigoLivroDuplicadoException(String codigo) {
        super("Já existe um livro cadastrado ultilizando esse código '" + codigo + "'.");
    }
}
