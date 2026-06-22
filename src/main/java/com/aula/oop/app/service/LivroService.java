package com.aula.oop.app.service;

import com.aula.oop.app.dto.*;
import com.aula.oop.app.exceptions.*;
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

    public LivroResponse salvar(LivroRequest dto) {

        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicado(
                    "Já existe um livro com este código.");
        }

        Livro livro = new Livro();

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        repository.save(livro);

        return converter(livro);
    }

    public List<LivroResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public LivroResponse buscar(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontrado("Livro não encontrado."));

        return converter(livro);
    }

    public LivroResponse atualizar(Long id, LivroRequest dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontrado("Livro não encontrado."));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        repository.save(livro);

        return converter(livro);
    }

    public void deletar(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontrado("Livro não encontrado."));

        repository.delete(livro);
    }

    private LivroResponse converter(Livro livro) {

        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }
}