package com.aula.oop.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LivroDTO {
    @NotBlank
    private long id;
    private String titulo;
    private String autor;
    private String codigo;
    private int anoPublicacao;
    private double preco;
}
