package com.aula.oop.app.exceptions;

public class CodigoDuplicado extends RuntimeException {

    public CodigoDuplicado(String codigo) {
        super("Já existe um livro cadastrado com o Código: " + codigo);
    }
}
