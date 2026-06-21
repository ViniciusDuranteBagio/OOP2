package com.aula.oop.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anoPublicacao;
    private Double preco;
}
