package com.biblioteca.livros.dto;

import com.biblioteca.livros.entity.Livro;
import java.math.BigDecimal;

public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anoPublicacao;
    private BigDecimal preco;

    public LivroResponseDTO() {}

    public LivroResponseDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.codigo = livro.getCodigo();
        this.anoPublicacao = livro.getAnoPublicacao();
        this.preco = livro.getPreco();
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCodigo() { return codigo; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public BigDecimal getPreco() { return preco; }
}
