package com.aula.oop.trabalhofinal.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException(Long id) {
        super("Livro com ID " + id + " não encontrado.");
    }
}