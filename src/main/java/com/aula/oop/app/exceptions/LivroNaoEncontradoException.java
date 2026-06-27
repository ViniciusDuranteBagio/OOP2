package com.aula.oop.app.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException(String message) {

        super(message);
    }
}
