package com.aula.oop.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
    private String titulo;
    private String autor;
    private String codigo;
    private int anoLancamento;
    private double preco;
}