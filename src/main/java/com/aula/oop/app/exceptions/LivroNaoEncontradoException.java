package com.aula.oop.app.exceptions;

public class LivroNaoEncontradoException extends RuntimeException{

    public LivroNaoEncontradoException(Long id){
        super("Livro com o ID " + id + " não foi encontrado!");
    }
}
