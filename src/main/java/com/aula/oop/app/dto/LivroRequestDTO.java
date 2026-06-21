package com.aula.oop.app.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 150, message = "O título deve ter entre 2 e 150 caracteres")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    @Size(min = 2, max = 100, message = "O autor deve ter entre 2 e 100 caracteres")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s.'-]+$",
            message = "O autor deve conter apenas letras, espaços, pontos, apóstrofos ou hífens"
    )
    private String autor;

    @NotBlank(message = "O código é obrigatório")
    @Size(min = 3, max = 20, message = "O código deve ter entre 3 e 20 caracteres")
    @Pattern(
            regexp = "^[A-Za-z0-9-]+$",
            message = "O código deve conter apenas letras, números e hífen (sem espaços ou símbolos especiais)"
    )
    private String codigo;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1450, message = "O ano de publicação deve ser maior ou igual a 1450")
    @Max(value = 2026, message = "O ano de publicação deve ser menor ou igual a 2026")
    private Integer anoPublicacao;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "O preço deve ter no máximo 2 casas decimais")
    private BigDecimal preco;
}