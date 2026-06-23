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
public class LivrosRequestDTO {

    @NotBlank(message = "Título obrigatório")
    private String titulo;

    @NotBlank(message = "Autor obrigatório")
    private String autor;

    @NotBlank(message = "Código obrigatório")
    private String codigo;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1000, message = "Ano de publicação inválido")
    @Max(value = 2026, message = "Ano de publicação inválido")
    private Integer anoPublicacao;

    @NotNull(message = "Preço obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private Double preco;
}