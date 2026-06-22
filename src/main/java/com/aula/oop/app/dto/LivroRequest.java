package com.aula.oop.app.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LivroRequest {

    @NotBlank(message = "O titulo e obrigatorio.")
    private String titulo;

    @NotBlank(message = "O autor e obrigatorio.")
    private String autor;

    @NotBlank(message = "O codigo e obrigatorio.")
    private String codigo;

    @NotNull(message = "O ano de publicacao e obrigatorio.")
    @Min(value = 1450, message = "O ano de publicacao deve ser maior ou igual a 1450.")
    @Max(value = 2100, message = "O ano de publicacao deve ser menor ou igual a 2100.")
    private Integer anoPublicacao;

    @NotNull(message = "O preco e obrigatorio.")
    @DecimalMin(value = "0.01", message = "O preco deve ser maior que zero.")
    private Double preco;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
