package com.aula.oop.app.exceptions;

public class CodigoJaExisteException extends RuntimeException {
    public CodigoJaExisteException(String codigo) {
        super("Já existe um livro cadastrado com o código: " + codigo);
    }
}
