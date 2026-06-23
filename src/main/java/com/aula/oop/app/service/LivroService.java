package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {

        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException("Já existe um livro com esse código.");
        }

        Livro livro = new Livro();

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        livro = repository.save(livro);

        LivroResponseDTO response = new LivroResponseDTO();

        response.setId(livro.getId());
        response.setTitulo(livro.getTitulo());
        response.setAutor(livro.getAutor());
        response.setCodigo(livro.getCodigo());
        response.setAnoPublicacao(livro.getAnoPublicacao());
        response.setPreco(livro.getPreco());

        return response;
    }

    public List<LivroResponseDTO> listar() {

        return repository.findAll().stream().map(livro -> {
            LivroResponseDTO dto = new LivroResponseDTO();

            dto.setId(livro.getId());
            dto.setTitulo(livro.getTitulo());
            dto.setAutor(livro.getAutor());
            dto.setCodigo(livro.getCodigo());
            dto.setAnoPublicacao(livro.getAnoPublicacao());
            dto.setPreco(livro.getPreco());

            return dto;
        }).collect(Collectors.toList());
    }

    public LivroResponseDTO buscar(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        LivroResponseDTO dto = new LivroResponseDTO();

        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setCodigo(livro.getCodigo());
        dto.setAnoPublicacao(livro.getAnoPublicacao());
        dto.setPreco(livro.getPreco());

        return dto;
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        livro = repository.save(livro);

        LivroResponseDTO response = new LivroResponseDTO();

        response.setId(livro.getId());
        response.setTitulo(livro.getTitulo());
        response.setAutor(livro.getAutor());
        response.setCodigo(livro.getCodigo());
        response.setAnoPublicacao(livro.getAnoPublicacao());
        response.setPreco(livro.getPreco());

        return response;
    }

    public void excluir(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        repository.delete(livro);
    }
}