package com.aula.oop.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;
    @Column(unique = true) //aqui ele garante que no banco o codigo seja unico
    private String codigo;

    private Integer anoPublicacao;

    private Double preco;

}