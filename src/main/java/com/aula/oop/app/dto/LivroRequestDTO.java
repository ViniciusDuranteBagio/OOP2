package com.aula.oop.app.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO {

    @NotBlank(message = "O titulo do livro e obrigatorio e nao pode ser vazio")
    private String titulo;

    @NotBlank(message = "O autor do livro e obrigatorio e nao pode ser vazio")
    private String autor;

    @NotBlank(message = "O codigo do livro e obrigatorio e nao pode ser vazio")
    private String codigo;

    @NotNull(message = "O ano de publicacao do livro e obrigatorio")
    @Min(value = 1440, message = "O ano de publicacao do livro deve ser maior ou igual a 1440")
    @Max(value = 2100, message = "O ano de publicacao do livro deve ser menor ou igual a 2100")
    private Integer anoPublicacao;

    @NotNull(message = "O preco do livro e obrigatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preco do livro deve ser maior que zero")
    private BigDecimal preco;

}
