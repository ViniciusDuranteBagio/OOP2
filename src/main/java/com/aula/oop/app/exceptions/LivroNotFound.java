package com.aula.oop.app.exceptions;

public class LivroNotFound extends RuntimeException{

    public LivroNotFound(Long id) {
        super("Livro não encontrado com ID: " +id);
    }
}
