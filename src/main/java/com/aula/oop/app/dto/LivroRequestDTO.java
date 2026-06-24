package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório!")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "O nome do autor é obrigatório!")
    private String autor;

    @NotBlank(message = "O código é obrigatório")
    private String codigo;

    //esse min e max eu vi que garantem que o ano fique nesse intervalo
    @Min(value = 1000, message = "Ano inválido! É aceito apenas acima do ano 1000")
    @Max(value = 2027, message = "Ano inválido! É aceito apenas abaixo do ano 2027")
    @NotNull(message = "O ano de publicação a obra é obrigatório!")
    private Integer anoPublicacao;

    @NotNull(message = "O preço é obrigatório!")
    @Positive(message = "O preço deve ser maior que zero")
    private Double preco;
}
