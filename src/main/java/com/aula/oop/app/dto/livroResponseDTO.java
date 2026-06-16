package com.aula.oop.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class livroResponseDTO {

    private long id;
    private String titulo;
    private String autor;
    private String codigo;
    private int anoPublicacao;
    private double preco;

}