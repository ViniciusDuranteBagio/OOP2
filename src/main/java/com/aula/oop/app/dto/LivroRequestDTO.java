package com.aula.oop.app.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequestDTO {

    @NotBlank(message = "Título obrigatótio")
    private String titulo;

    @NotBlank(message = "Autor obrigatótio")
    private String autor;

    @NotBlank(message = "Código obrigatótio")
    private String codigo;

    @NotNull(message = "Ano de publicação é obrigatório")
    @Min(value = 1000, message = "Ano de publicação inválido")
    @Max(value = 2026, message = "Ano de publicação inválido")
    private Integer anoPublicacao;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "preço deve ser maior qe zero")
    private Double preco;

}
