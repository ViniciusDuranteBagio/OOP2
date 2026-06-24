package com.aula.oop.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false)
    private Double preco;

    // Construtores
    public Livro() {}

    public Livro(Long id, String titulo, String autor, String codigo, Integer anoPublicacao, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}