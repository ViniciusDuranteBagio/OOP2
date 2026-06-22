package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public LivroDTO cadastrar(LivroDTO dto) {

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

        dto.setId(livro.getId());

        return dto;
    }

    public List<LivroDTO> listar() {

        return repository.findAll().stream().map(livro -> {
            LivroDTO dto = new LivroDTO();

            dto.setId(livro.getId());
            dto.setTitulo(livro.getTitulo());
            dto.setAutor(livro.getAutor());
            dto.setCodigo(livro.getCodigo());
            dto.setAnoPublicacao(livro.getAnoPublicacao());
            dto.setPreco(livro.getPreco());

            return dto;
        }).collect(Collectors.toList());

    }

    public LivroDTO buscar(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        LivroDTO dto = new LivroDTO();

        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setCodigo(livro.getCodigo());
        dto.setAnoPublicacao(livro.getAnoPublicacao());
        dto.setPreco(livro.getPreco());

        return dto;
    }

    public LivroDTO atualizar(Long id, LivroDTO dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        repository.save(livro);

        dto.setId(livro.getId());

        return dto;
    }

    public void excluir(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        repository.delete(livro);
    }

}