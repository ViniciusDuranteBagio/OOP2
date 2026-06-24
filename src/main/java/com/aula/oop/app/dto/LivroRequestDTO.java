package com.aula.oop.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório.")
    private String autor;

    @NotBlank(message = "O código é obrigatório.")
    private String codigo;

    @NotNull(message = "O ano de publicação é obrigatório.")
    @Min(value = 1000, message = "Ano de publicação inválido.")
    private Integer anoPublicacao;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;

}