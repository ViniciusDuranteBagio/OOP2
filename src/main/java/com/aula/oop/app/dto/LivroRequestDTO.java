package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequestDTO {
    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotBlank
    private String codigo;

    @NotNull
    @Min(1)
    @Max(2026)
    private Integer anoPublicacao;

    @NotNull
    @DecimalMin("0.000001")
    private Double preco;
}