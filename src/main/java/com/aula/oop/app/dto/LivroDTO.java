package com.aula.oop.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;

public record LivroDTO(
        @NotNull(message = "O título é obrigatório e não pode ser nulo")
        String titulo,

        @NotNull(message = "O autor é obrigatório e não pode ser nulo")
        String autor,

        @NotBlank(message = "O código é obrigatório e não pode ser nulo ou vazio")
        String codigo,

        @NotNull(message = "O ano de publicação é obrigatório")
        @Positive(message = "O ano de publicação deve ser um número positivo")
        @PastOrPresent(message = "O ano de publicação não pode ser um ano no futuro")
        Integer anoPublicacao,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        Double preco
) {}
