package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record LivroRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "Autor é obrigatório")
        String autor,

        @NotBlank(message = "Código é obrigatório")
        String codigo,

        @NotNull(message = "Ano de publicação é obrigatório")
        @Min(value = 1500, message = "Ano inválido")
        @Max(value = 2100, message = "Ano inválido")
        Integer anoPublicacao,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
        BigDecimal preco

) {}