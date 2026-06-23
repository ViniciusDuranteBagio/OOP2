package com.aula.oop.app.exceptions;

public class CodigoJaCadastradoException extends RuntimeException {
    public CodigoJaCadastradoException(String codigo) {
        super("Já existe um livro cadastrado com o código: " + codigo);
    }
}