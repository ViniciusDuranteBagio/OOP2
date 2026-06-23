package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO {

    @NotBlank(message = "O título não pode ser vazio")
    private String titulo;

    @NotBlank(message = "O autor não pode ser vazio")
    private String autor;

    @NotBlank(message = "O código não pode ser vazio")
    private String codigo;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1000, message = "O ano de publicação deve ser válido")
    @Max(value = 2100, message = "O ano de publicação deve ser válido")
    private Integer anoPublicacao;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private Double preco;
}