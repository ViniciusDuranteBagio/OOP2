package com.aula.oop.app;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import static jakarta.persistence.GenerationType.IDENTITY;


public class livro {
    private static final Object GenerationType = ;r
    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anodePublicação;
    private double preço;

     this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.anodePublicação = anodePublicação;
        this.preço = preço;

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    @Entity
    @Table(name = "livros")
    public class Livro {

        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;

        @NotBlank(message = "O título é obrigatório")
        @Column(nullable = false)
        private String titulo;

        @NotBlank(message = "O autor é obrigatório")
        @Column(nullable = false)
        private String autor;

        @NotBlank(message = "O código é obrigatório")
        @Column(nullable = false, unique = true)
        private String codigo;

        @NotNull(message = "O ano de publicação é obrigatório")
        @Column(nullable = false)
        private Integer anoPublicacao;

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        @Column(nullable = false)
        private Double preco;

        public void getCodigo() {
            return 0;
        }
    }}

