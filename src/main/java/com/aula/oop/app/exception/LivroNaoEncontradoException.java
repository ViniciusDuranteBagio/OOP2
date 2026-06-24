package com.aula.oop.app.exception;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException(String message) {
        super(message);
    }
}