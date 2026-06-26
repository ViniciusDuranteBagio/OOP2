package com.aula.oop.app.service;


import com.aula.oop.app.dto.*;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.model.Livros;
import com.aula.oop.app.repository.LivroRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<Livros> listarTodos() {
        return repository.findAll();
    }

    public Livros buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Livro não encontrado"));
    }

    public Livros criar(LivroRequestDTO dto) {

        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException(
                    "Já existe um livro com esse código");
        }

        Livros livro = new Livros();

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        return repository.save(livro);
    }

    public Livros atualizar(Long id,
                            LivroRequestDTO dto) {

        Livros livro = buscarPorId(id);

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        return repository.save(livro);
    }

    public void deletar(Long id) {

        Livros livro = buscarPorId(id);

        repository.delete(livro);
    }
}