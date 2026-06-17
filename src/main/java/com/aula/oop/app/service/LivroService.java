package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO createTask(LivroDTO livroDTO) {
        if (livroRepository.existsByCodigo(livroDTO.getCodigo())) {
            throw new RuntimeException("Já existe um livro cadastrado com este código");
        }

        Livro entity = dtoToEntity(livroDTO);
        return entityToResponseDTO(livroRepository.save(entity));
    }

    public List<LivroResponseDTO> getAllTasks() {
        return livroRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public LivroResponseDTO getById(Long id) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        return entityToResponseDTO(livro);
    }

    public LivroResponseDTO updateTask(Long id, LivroDTO livroDTO) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (!livro.getCodigo().equals(livroDTO.getCodigo())
                && livroRepository.existsByCodigo(livroDTO.getCodigo())) {
            throw new RuntimeException("Já existe um livro cadastrado com este código");
        }

        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setCodigo(livroDTO.getCodigo());
        livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
        livro.setPreco(livroDTO.getPreco());

        return entityToResponseDTO(livroRepository.save(livro));
    }

    public void deleteTask(Long id) {
        livroRepository.deleteById(id);
    }

    private Livro dtoToEntity(LivroDTO dto) {
        Livro entity = new Livro();

        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        entity.setCodigo(dto.getCodigo());
        entity.setAnoPublicacao(dto.getAnoPublicacao());
        entity.setPreco(dto.getPreco());

        return entity;
    }

    private LivroResponseDTO entityToResponseDTO(Livro entity) {
        LivroResponseDTO dto = new LivroResponseDTO();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setAutor(entity.getAutor());
        dto.setCodigo(entity.getCodigo());
        dto.setAnoPublicacao(entity.getAnoPublicacao());
        dto.setPreco(entity.getPreco());

        return dto;
    }
}