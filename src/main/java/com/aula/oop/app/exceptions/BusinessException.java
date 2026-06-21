package com.aula.oop.app.exceptions;

// BusinessException -> exececao customizada lancada quando uma REGRA DE NEGOCIO
// e violada. Exemplo principal no nosso projeto: tentar cadastrar um livro
// com um "codigo" que ja existe em outro livro.
//
// E diferente de erro de validacao (formato/tipo de dado) e diferente de
// "recurso nao encontrado": aqui o dado esta tecnicamente bem formado,
// mas a OPERACAO em si nao pode ser realizada por uma regra do sistema.
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
