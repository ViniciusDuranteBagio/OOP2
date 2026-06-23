package com.aula.oop.app.dto;

import com.aula.oop.app.model.Livro;

public record LivroResponseDTO(
        Long id,
        String titulo,
        String autor,
        String codigo,
        Integer anoPublicacao,
        Double preco
) {
    public static LivroResponseDTO deEntidade(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }
}