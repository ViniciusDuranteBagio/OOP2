package com.aula.oop.app.exceptions;

public class LivroNotFeoundException extends RuntimeException {

    public LivroNotFeoundException(String mensagem) {

        super(mensagem);
    }

}
