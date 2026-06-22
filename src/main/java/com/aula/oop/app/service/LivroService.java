package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
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

    public Livro cadastrar(LivroDTO dto) {

        if (repository.findByCodigo(dto.getCodigo()).isPresent()) {
            throw new CodigoDuplicadoException("Código já cadastrado.");
        }

        Livro livro = new Livro();

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        return repository.save(livro);
    }

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));
    }

    public Livro atualizar(Long id, LivroDTO dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));

        repository.findByCodigo(dto.getCodigo())
                .ifPresent(l -> {
                    if (!l.getId().equals(id)) {
                        throw new CodigoDuplicadoException("Código já cadastrado.");
                    }
                });

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        return repository.save(livro);
    }

    public void deletar(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));

        repository.delete(livro);
    }
}