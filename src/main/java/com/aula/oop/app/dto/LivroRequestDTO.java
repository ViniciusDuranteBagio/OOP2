package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LivroRequestDTO {
    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "Código é obrigatório")
    private String codigo;

    @NotNull(message = "Ano é obrigatório")
    @Min(value = 1500, message = "Ano inválido")
    @Max(value = 2100, message = "Ano inválido")
    private Integer anoPublicacao;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private BigDecimal preco;
}
