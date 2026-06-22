package com.aula.oop.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Título é obrigatório")
    private String titulo;
    @NotBlank(message = "Autor é obrigatório")
    private String autor;
    @NotBlank(message = "Código é obrigatório")
    @Column(unique = true)
    private String codigo;
    @NotNull(message = "Ano de publicacao é obrigatório")
    @Min(value = 0000, message = "Ano deve ser posterior a 0000")
    @Max(value = 2026, message = "Ano deve ser inferior a 2026")
    private int anoPublicacao;
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private double preco;
}
