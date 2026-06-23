package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {

        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicadoException("Já existe um livro com esse código.");
        }

        Livro livro = new Livro();

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        Livro salvo = repository.save(livro);

        return converterParaResponse(salvo);
    }

    public List<LivroResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));

        return converterParaResponse(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        Livro atualizado = repository.save(livro);

        return converterParaResponse(atualizado);
    }

    public void excluir(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));

        repository.delete(livro);
    }

    private LivroResponseDTO converterParaResponse(Livro livro) {

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