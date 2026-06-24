package com.aula.oop.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "livros", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;

    @Column(nullable = false, unique = true)
    private String codigo;
    private Integer anoPublicacao;
    private Double preco;
    public Livro() {
    }

    public Livro(Long id, String titulo, String autor, String codigo, Integer anoPublicacao, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
    }

}