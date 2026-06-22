package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequestDTO {

    @NotBlank(message = "Título não pode ser vazio ou nulo")
    private String titulo;

    @NotBlank(message = "Autor não pode ser vazio ou nulo")
    private String autor;

    @NotBlank(message = "Código não pode ser vazio ou nulo")
    private String codigo;

    @NotNull(message = "Ano de publicação não pode ser nulo")
    @Min(value = 1500, message = "Ano inválido")
    @Max(value = 2100, message = "Ano inválido")
    private Integer anoPublicacao;

    @NotNull(message = "Preço não pode ser nulo")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;
}