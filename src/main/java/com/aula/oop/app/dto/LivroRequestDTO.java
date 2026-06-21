package com.aula.oop.app.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "Código é obrigatório")
    private String codigo;

    @NotNull(message = "Ano de publicação é obrigatório")
    @Min(value = 1000, message = "Ano de publicação inválido")
    @Max(value = 2026, message = "Ano de publicação inválido")
    private Integer anoPublicacao;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private Double preco;
}