package com.aula.oop.app.exceptions;

public class LivroExistenteComEsseCodigo extends RuntimeException{

    public LivroExistenteComEsseCodigo (String codigo){
        super("Já existe um livro com o código " + codigo + " , escolha outra código!");
    }
}
