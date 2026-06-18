package com.aula.oop.app.dto;

import com.aula.oop.app.model.Livro;

public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anoPublicacao;
    private Double preco;

    public LivroResponseDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.codigo = livro.getCodigo();
        this.anoPublicacao = livro.getAnoPublicacao();
        this.preco = livro.getPreco();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public Double getPreco() {
        return preco;
    }
}