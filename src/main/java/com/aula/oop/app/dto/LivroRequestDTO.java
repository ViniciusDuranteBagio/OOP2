package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotBlank(message = "O código é obrigatório")
    private String codigo;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1000, message = "O ano de publicação deve ser maior ou igual a 1000")
    @Max(value = 2026, message = "O ano de publicação não pode ser maior que 2026")
    private Integer anoPublicacao;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}