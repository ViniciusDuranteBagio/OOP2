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
            throw new RuntimeException("Já existe um livro com esse código");
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

    public List<LivroResponseDTO> listarTodos() {

        return repository.findAll()
                .stream()
                .map(livro -> {

                    LivroResponseDTO response = new LivroResponseDTO();

                    response.setId(livro.getId());
                    response.setTitulo(livro.getTitulo());
                    response.setAutor(livro.getAutor());
                    response.setCodigo(livro.getCodigo());
                    response.setAnoPublicacao(livro.getAnoPublicacao());
                    response.setPreco(livro.getPreco());

                    return response;

                }).collect(Collectors.toList());
    }

    public LivroResponseDTO buscarPorId(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException(
                                "Livro não encontrado com ID: " + id));

        LivroResponseDTO response = new LivroResponseDTO();

        response.setId(livro.getId());
        response.setTitulo(livro.getTitulo());
        response.setAutor(livro.getAutor());
        response.setCodigo(livro.getCodigo());
        response.setAnoPublicacao(livro.getAnoPublicacao());
        response.setPreco(livro.getPreco());

        return response;
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException(
                                "Livro não encontrado com ID: " + id));

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

    public void deletar(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException(
                                "Livro não encontrado com ID: " + id));

        repository.delete(livro);
    }
}