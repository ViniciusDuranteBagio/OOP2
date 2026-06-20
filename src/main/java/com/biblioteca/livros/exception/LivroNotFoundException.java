package com.biblioteca.livros.exception;

public class LivroNotFoundException extends RuntimeException {

    public LivroNotFoundException(Long id) {
        super("livro nao encontrado com id: " + id);
    }
}
