package com.aula.oop.trabalhofinal.exceptions;

public class CodigoDuplicadoException extends RuntimeException {

    public CodigoDuplicadoException(String codigo) {
        super("Já existe um livro com o código: " + codigo);
    }
}