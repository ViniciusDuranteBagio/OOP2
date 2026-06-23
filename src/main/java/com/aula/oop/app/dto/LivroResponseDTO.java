package com.aula.oop.app.dto;


import com.aula.oop.app.model.Livro;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               //criar getters, setters, hash equals e equals
@NoArgsConstructor  //criar construtor que não precisa passar dados
@AllArgsConstructor //criar construtor que precisa passar dados
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigo;
    private Integer anoPublicacao;
    private Double preco;

    public LivroResponseDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.codigo = livro.getCodigo();
        this.anoPublicacao = livro.getAnoPublicacao();
        this.preco = livro.getPreco();
    }
}
