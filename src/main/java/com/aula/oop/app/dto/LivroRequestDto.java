package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record LivroRequestDto(

        @NotBlank(message = "O titulo do livro não pode ser nulo")
        String titulo,

        @NotBlank(message = "O autor do livro não pode ser nulo")
        String autor,

        @NotBlank(message = "O código do livro não pode ser nulo")
        String codigo,

        @NotNull(message = "O ano de publicação não pode ser nulo")
        @Min(value = 1455)
        @Max(value = 2027, message = "O ano de publicação não pode ser maior que o próximo ano.")
        Integer anoPublicacao,

        @NotNull(message = "O código do livro não pode ser nulo")
        @Positive
        BigDecimal preco

) {
}
