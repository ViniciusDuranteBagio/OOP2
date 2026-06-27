package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroDTO {

    @NotBlank(message = "Inserir título é obrigatório")
    private String titulo;

    @NotBlank(message = "Inserir autor é obrigatório")
    private String autor;

    @NotBlank(message = "Inserir código é obrigatório")
    private String codigo;

    @NotNull(message = "Informar o ano é obrigatório")
    @Min(1000)
    @Max(2100)
    private Integer anoPublicacao;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;


}
