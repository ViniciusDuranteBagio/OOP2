package com.aula.oop.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anoPublicacao;
    private Double preco;

    public LivroResponseDTO() {
    }

    public LivroResponseDTO(Long id, String titulo, String autor, String codigo, Integer anoPublicacao, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
    }

}