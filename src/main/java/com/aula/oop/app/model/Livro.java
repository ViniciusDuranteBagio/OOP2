package com.aula.oop.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long id;
    private String titulo;
    private String autor;

    @Column(unique = true)
    private String codigo;
    private Integer anoPublicação;
    private Double preco;


}

