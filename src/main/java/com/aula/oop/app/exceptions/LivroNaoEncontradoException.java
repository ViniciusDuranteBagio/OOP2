package com.aula.oop.app.exceptions;

public class LivroNaoEncontradoException extends BusinessException {
    public LivroNaoEncontradoException(String message) {
        super(message);
    }
}