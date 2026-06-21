package com.aula.oop.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anoPublicacao;
    private Double preco;
}