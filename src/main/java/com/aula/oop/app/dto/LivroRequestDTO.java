package com.aula.oop.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LivroRequestDTO {

    @NotBlank(message = "O título do Livro é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "Necessário informar o nome do autor")
    private String autor;

    @NotBlank(message = "O código do Livro é obrigatório")
    @Column(unique = true)
    private String codigo;

    @NotNull(message = "Informe o Ano de Lançamento do Livro")
    @Min(value = 1450, message = "O ano de lançamento deve ser maior ou igual a 1500")
    @Max(value = 2999, message = "O ano de lançamento deve ser menor que 2999")
    private int anoLancamento;

    @NotNull(message = "Informe o preço do livro")
    @Positive(message = "Preço deve ser maior que 0")
    private double preco;




}