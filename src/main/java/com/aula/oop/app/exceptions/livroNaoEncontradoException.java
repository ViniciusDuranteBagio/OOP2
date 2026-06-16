package com.aula.oop.app.exceptions;

public class livroNaoEncontradoException extends RuntimeException {

  public livroNaoEncontradoException(Long id) {
    super("O Livro com ID " + id + " não foi encontrado.");
  }
}