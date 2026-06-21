package com.aula.oop.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// ErrorResponse -> formato PADRAO que TODA resposta de erro da nossa API vai usar.
// Isso garante consistencia: independente do tipo de erro (validacao, regra de
// negocio, recurso nao encontrado, erro generico), o cliente sempre recebe
// um JSON com a mesma "forma", facilitando o tratamento no front-end/consumidor da API.
//
// Exemplo de JSON gerado por esta classe:
// {
//   "timestamp": "2026-06-21T10:30:00",
//   "status": 404,
//   "error": "Recurso nao encontrado",
//   "message": "Livro com id 99 nao encontrado",
//   "details": null
// }
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    // Data e hora exata em que o erro ocorreu (util para logs/depuracao)
    private LocalDateTime timestamp;

    // Codigo HTTP numerico (404, 400, 409, 500...)
    private int status;

    // Nome curto/categoria do erro (ex: "Recurso nao encontrado", "Erro de validacao")
    private String error;

    // Mensagem principal, amigavel, explicando o que aconteceu
    private String message;

    // Lista de detalhes extras -> usada principalmente nos erros de VALIDACAO,
    // onde podem existir varios campos invalidos ao mesmo tempo
    // (ex: ["titulo: nao pode ser vazio", "preco: deve ser maior que zero"])
    private List<String> details;

}
