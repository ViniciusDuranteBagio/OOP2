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

// LivroRequestDTO -> representa os dados que o CLIENTE ENVIA para a API
// (no corpo do POST e do PUT). E aqui que colocamos as anotacoes de VALIDACAO,
// pois e a entrada de dados que precisamos proteger contra valores invalidos.
//
// Importante: este DTO NAO tem o campo "id", porque o ID e gerado pelo
// banco/servidor, o cliente nunca deveria poder definir o id de um livro novo.

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO {

    // @NotBlank -> garante que o campo nao seja nulo, nem vazio ("")
    // e nem apenas espacos em branco ("   "). Mais rigoroso que @NotNull para Strings.
    @NotBlank(message = "O titulo e obrigatorio e nao pode estar vazio")
    private String titulo;

    @NotBlank(message = "O autor e obrigatorio e nao pode estar vazio")
    private String autor;

    @NotBlank(message = "O codigo e obrigatorio e nao pode estar vazio")
    private String codigo;

    // @NotNull -> usado aqui (em vez de @NotBlank) porque o campo e um Integer,
    // nao uma String. Nao faz sentido falar em "vazio" para um numero.
    @NotNull(message = "O ano de publicacao e obrigatorio")
    @Min(value = 1440, message = "O ano de publicacao deve ser maior ou igual a 1440")
    // 1440 e aproximadamente o ano da invencao da impressao grafica (Gutenberg),
    // um limite razoavel para "ano de publicacao de um livro"
    @Max(value = 2100, message = "O ano de publicacao deve ser menor ou igual a 2100")
    // Limite superior para evitar anos "futuros demais" digitados por engano
    private Integer anoPublicacao;

    @NotNull(message = "O preco e obrigatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preco deve ser maior que zero")
    // inclusive = false -> significa que o ZERO NAO e um valor aceito,
    // o preco precisa ser estritamente MAIOR que 0.0, conforme exigido no enunciado
    private BigDecimal preco;

}
