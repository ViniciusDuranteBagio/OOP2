package com.aula.oop.app.livros.dto;

import com.aula.oop.app.livros.entity.Livro;
import java.math.BigDecimal;

public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anoPublicacao;
    private BigDecimal preco;

    public LivroResponseDTO() {
    }

    public LivroResponseDTO(Livro livro) {
        id = livro.getId();
        titulo = livro.getTitulo();
        autor = livro.getAutor();
        codigo = livro.getCodigo();
        anoPublicacao = livro.getAnoPublicacao();
        preco = livro.getPreco();
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

    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "LivroResponseDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", codigo='" + codigo + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", preco=" + preco +
                '}';
    }
}