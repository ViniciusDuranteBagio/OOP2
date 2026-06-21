package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
    @NotBlank
    private String titulo;
    @NotBlank
    private String autor;
    @NotBlank
    @UniqueElements
    private String codigo;
    @NotNull
    @Max(2026)
    private Integer anoPublicacao;

    @NotNull
    @DecimalMin("0.000001")
    private Double preco;
    }

