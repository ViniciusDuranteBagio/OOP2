package com.aula.oop.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// LivroResponseDTO -> representa os dados que a API DEVOLVE para o cliente
// (no corpo das respostas de GET, POST, PUT). Aqui SIM incluimos o "id",
// pois o cliente precisa saber o id do livro para poder buscar/atualizar/excluir depois.
//
// Esse DTO NAO tem nenhuma anotacao de validacao, pois validacao so faz sentido
// em dados de ENTRADA (o que o cliente nos envia), nao em dados de SAIDA.
//
// Ter DTOs separados de Request e Response e o que garante o requisito
// "a entidade nao deve ser exposta diretamente nas requisicoes e respostas da API".

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anoPublicacao;
    private BigDecimal preco;

}
