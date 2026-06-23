package com.aula.oop.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_titulo")
    private String titulo;

    @Column(name = "nm_autor")
    private String autor;

    @Column(name = "nr_codigo")
    private String codigo;

    @Column(name = "dt_publicacao")
    private Integer anoPublicacao;

    @Column(name = "vl_preco")
    private BigDecimal preco;
}