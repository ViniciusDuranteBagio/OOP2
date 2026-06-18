package com.aula.oop.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDTO {
    private long id;
    private String titulo;
    private String autor;
    private String codigo;
    private int anoPublicacao;
    private double preco;
}

