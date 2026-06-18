package com.aula.oop.app.dto;


import com.aula.oop.app.livro;
import jakarta.validation.constraints.*;

public class LivroDTO {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "Código é obrigatório")
    private String codigo;

    @NotNull(message = "Ano de publicação é obrigatório")
    @Min(value = 1000, message = "Ano de publicação deve ser maior que 1000")
    @Max(value = 2026, message = "Ano de publicação não pode ser no futuro")
    private Integer anoPublicacao;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;

    // Construtor vazio
    public LivroDTO() {
    }

    // Construtor que transforma a nossa entidade "livro" neste DTO
    public LivroDTO(livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.codigo = livro.getCodigo();
        this.anoPublicacao = livro.getAnoPublicacao();
        this.preco = livro.getPreco();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
