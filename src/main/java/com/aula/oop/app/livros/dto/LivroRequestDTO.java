package com.aula.oop.app.livros.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;


public class LivroRequestDTO {

    @NotBlank(message = "titulo nao pode ser vazio")


    private String titulo;

    @NotBlank(message = "autor nao pode ser vazio")


    private String autor;

    @NotBlank(message = "codigo obrigatorio")


    private String codigo;

    @NotNull(message = "ano de publicacao obrigatorio")
    @Min(value = 1000, message = "ano invalido, minimo 1000")
    @Max(value = 2026, message = "ano invalido nao pode ser futuro")


    private Integer anoPublicacao;

    @NotNull(message = "preco obrigatorio")
    @DecimalMin(value = "0.01", message = "preco deve ser maior que zero")

    private BigDecimal preco;

    public LivroRequestDTO() {}



    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

            public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Integer getAnoPublicacao() { return anoPublicacao; }


    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

             public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}
