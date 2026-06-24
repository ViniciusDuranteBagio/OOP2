package com.aula.oop.app.dto;


import jakarta.validation.constraints.*;

public class LivroRequestDTO {

    @NotBlank(message = "Você precisa informar um título. ")
    private String titulo;

    @NotBlank(message = "Como vou saber quem lançou esse livro se você não informar um autor?Sou uma API, não uma IA.")
    private String autor;

    @NotBlank(message = "O código é obrigatório meu bem.")
    private String codigo;

    @NotNull(message = "Como vou saber em que ano esse livro lançou? Poderia por gentileza informar o ano de publicação?")
    @Min(value = 1000, message = "O ano deve ser maior que 1000.")
    @Max(value = 2028, message = "O ano deve ser menor que 2028")
    private Integer anoPublicacao;

    @NotNull(message = "O preço é obrigatório.Ou vai querer que o livro saia de graça?")
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