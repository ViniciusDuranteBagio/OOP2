package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exception.CodigoDuplicadoException;
import com.aula.oop.app.exception.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepositorio repositorio;

    public LivroService(LivroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // CREATE
    public LivroResponseDTO criar(Livro livro) {

        repositorio.findByCodigo(livro.getCodigo())
                .ifPresent(l -> {
                    throw new CodigoDuplicadoException("Código já existe");
                });

        return toDTO(repositorio.save(livro));
    }

    // LISTAR
    public List<LivroResponseDTO> listar() {
        return repositorio.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // BUSCAR POR ID
    public LivroResponseDTO buscarPorId(Long id) {

        Livro livro = repositorio.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        return toDTO(livro);
    }

    // UPDATE
    public LivroResponseDTO atualizar(Long id, Livro livroAtualizado) {

        Livro livro = repositorio.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        repositorio.findByCodigo(livroAtualizado.getCodigo())
                .filter(l -> !l.getId().equals(id))
                .ifPresent(l -> {
                    throw new CodigoDuplicadoException("Código já existe");
                });

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setCodigo(livroAtualizado.getCodigo());
        livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        livro.setPreco(livroAtualizado.getPreco());

        return toDTO(repositorio.save(livro));
    }

    // DELETE
    public void deletar(Long id) {

        Livro livro = repositorio.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        repositorio.delete(livro);
    }

    // CONVERSÃO
    private LivroResponseDTO toDTO(Livro livro) {

        LivroResponseDTO dto = new LivroResponseDTO();

        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setCodigo(livro.getCodigo());
        dto.setAnoPublicacao(livro.getAnoPublicacao());
        dto.setPreco(livro.getPreco());

        return dto;
    }
}