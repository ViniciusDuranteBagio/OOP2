package com.aula.oop.app;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_livros")
public class livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;

    @Column(unique = true)
    private String codigo;

    private Integer anoPublicacao;
    private Double preco;

    // Construtor vazio (obrigatório para o banco de dados)
    public livro() {
    }

    // Construtor completo para ajudar o Service a criar livros
    public livro(Long id, String titulo, String autor, String codigo, Integer anoPublicacao, Double preco) {
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
    public void setTitulo(String titulo ) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}