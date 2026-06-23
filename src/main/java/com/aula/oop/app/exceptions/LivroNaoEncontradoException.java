package com.aula.oop.app.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LivroNaoEncontradoException() {
        super("Livro não encontrado");
    }

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}