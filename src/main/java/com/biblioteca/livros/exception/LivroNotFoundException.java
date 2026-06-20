package com.biblioteca.livros.exception;

public class LivroNotFoundException extends RuntimeException {

    public LivroNotFoundException(Long id) {
        super("Livro não encontrado com o ID: " + id);
    }
}
