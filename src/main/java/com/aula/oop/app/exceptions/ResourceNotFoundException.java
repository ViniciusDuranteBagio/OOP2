package com.aula.oop.app.exceptions;

// ResourceNotFoundException -> exececao customizada lancada quando o cliente
// pede um recurso (ex: um Livro por ID) que nao existe no banco.
//
// Estende RuntimeException (e nao Exception comum) para ser uma "unchecked exception":
// assim no Service/Controller nao precisamos declarar "throws" em toda assinatura de metodo.
public class ResourceNotFoundException extends RuntimeException {

    // O construtor simplesmente recebe a mensagem de erro e passa para a
    // superclasse (RuntimeException), que e quem realmente guarda essa mensagem
    // e a devolve depois via getMessage().
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
