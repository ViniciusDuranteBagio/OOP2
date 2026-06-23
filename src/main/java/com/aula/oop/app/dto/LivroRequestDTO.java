package com.aula.oop.app.dto;


import jakarta.validation.constraints.*;

public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório.")
    private String autor;

    @NotBlank(message = "O código é obrigatório.")
    private String codigo;

    @NotNull(message = "O ano de publicação é obrigatório.")
    @Min(value = 1000, message = "O ano deve ser maior que 1000.")
    @Max(value = 2100, message = "O ano deve ser menor que 2100.")
    private Integer anoPublicacao;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}