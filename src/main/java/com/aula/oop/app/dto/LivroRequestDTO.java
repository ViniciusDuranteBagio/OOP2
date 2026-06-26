package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class LivroRequestDTO {

    @NotBlank(message = "Muito  legal o livro me manda o titulo ai")
    private String titulo;

    @NotBlank(message = "Legal esse livro, só que agora manda com autor para eu ver um negocio")
    private String autor;

    @NotBlank(message = "Mas dai você vai indentificar como se não tem o codigo")
    private String codigo;

    @NotNull(message = "Ano de publicação é obrigatório")
    @Min(value = 0, message = "Menor que zero? é serio?")
    @Max(value = 2027, message = "não pode depois de 2027")
    private Integer anoPublicacao;

    @NotNull(message = "Vai ser de graça o negocio? informa um valor ai")
    @Positive(message = "Você vai dar dinheiro pro cliente? me da um valor maio que zero")
    private Double preco;
}