package com.aula.oop.app.exceptions;

public class LivroNaoEncontrado extends RuntimeException {

    public LivroNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}