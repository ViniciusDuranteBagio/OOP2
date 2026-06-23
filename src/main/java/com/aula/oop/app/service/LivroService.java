package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDto;
import com.aula.oop.app.dto.LivroResponseDto;
import com.aula.oop.app.exceptions.CodigoJaCadastradoException;
import com.aula.oop.app.exceptions.LivroNotFeoundException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<LivroResponseDto> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public LivroResponseDto findById(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNotFeoundException("Livro não encontrado: " + id));
        return toResponse(livro);
    }

    public LivroResponseDto create(LivroRequestDto dto) {
        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoJaCadastradoException("Já existe um livro com o código: " + dto.getCodigo());
        }

        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        return toResponse(repository.save(livro));
    }

    public LivroResponseDto update(Long id, LivroRequestDto dto) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNotFeoundException("Livro não encontrado: " + id));

        if (repository.existsByCodigoAndIdNot(dto.getCodigo(), id)) {
            throw new CodigoJaCadastradoException("Já existe um livro com o código: " + dto.getCodigo());
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        return toResponse(repository.save(livro));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new LivroNotFeoundException("Livro não encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private LivroResponseDto toResponse(Livro livro) {
        return new LivroResponseDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }

}
