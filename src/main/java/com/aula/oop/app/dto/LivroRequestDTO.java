package com.aula.oop.app.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class LivroRequestDTO {

    @NotBlank(message = "título é obrigatório")
    private String titulo;

    @NotBlank(message = "IQnforme o autor")
    private String autor;

    @NotBlank(message = "Código é obrigatório")
    private String codigo;

    @NotNull(message = "É preciso inserir o ano")
    @Min(1000)
    @Max(2100)
    private Integer anoPublicacao;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01")
    private BigDecimal preco;

    public LivroRequestDTO() {
    }

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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}