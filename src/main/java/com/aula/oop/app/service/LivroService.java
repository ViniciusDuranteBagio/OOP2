package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicado;
import com.aula.oop.app.exceptions.LivroNotFound;
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

    public List<LivroResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO findById(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNotFound(id));
        return toResponse(livro);
    }

    public LivroResponseDTO create(LivroRequestDTO dto) {
        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicado(dto.getCodigo());
        }
        Livro livro = toEntity(dto);
        return toResponse(repository.save(livro));
    }

    public LivroResponseDTO update(Long id, LivroRequestDTO dto) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNotFound(id));

        if (repository.existsByCodigoAndIdNot(dto.getCodigo(), id)) {
            throw new CodigoDuplicado(dto.getCodigo());
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
            throw new LivroNotFound(id);
        }
        repository.deleteById(id);
    }

    private LivroResponseDTO toResponse(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }

    private Livro toEntity(LivroRequestDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
        return livro;
    }
}