package com.aula.oop.app.service;

import com.aula.oop.app.dto.livroRequestDTO;
import com.aula.oop.app.dto.livroResponseDTO;
import com.aula.oop.app.exceptions.codigoLivroDuplicadoException;
import com.aula.oop.app.exceptions.livroNaoEncontradoException;
import com.aula.oop.app.model.livro;
import com.aula.oop.app.repository.livroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class livroService {

    private final livroRepository livroRepository;

    public livroService(livroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public livroResponseDTO createTask(livroRequestDTO livroRequestDTO) {
        if (livroRepository.existsByCodigo(livroRequestDTO.getCodigo())) {
            throw new codigoLivroDuplicadoException(livroRequestDTO.getCodigo());
        }

        livro entity = dtoToEntity(livroRequestDTO);
        return entityToResponseDTO(livroRepository.save(entity));
    }

    public List<livroResponseDTO> getAllLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public livroResponseDTO getById(Long id) {

        livro livro = buscarOuFalhar(id);

        return entityToResponseDTO(livro);
    }

    public livroResponseDTO updateTask(Long id, livroRequestDTO livroRequestDTO) {

        livro livro = buscarOuFalhar(id);

        if (livroRepository.existsByCodigoAndIdNot(livroRequestDTO.getCodigo(), id)) {
            throw new codigoLivroDuplicadoException(livroRequestDTO.getCodigo());
        }

        livro.setTitulo(livroRequestDTO.getTitulo());
        livro.setAutor(livroRequestDTO.getAutor());
        livro.setCodigo(livroRequestDTO.getCodigo());
        livro.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());
        livro.setPreco(livroRequestDTO.getPreco());

        return entityToResponseDTO(livroRepository.save(livro));
    }

    public void deleteTask(Long id) {
        livro livro = buscarOuFalhar(id);
        livroRepository.delete(livro);
    }

    private livro buscarOuFalhar(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new livroNaoEncontradoException(id));
    }

    private livro dtoToEntity(livroRequestDTO dto) {
        livro entity = new livro();

        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        entity.setCodigo(dto.getCodigo());
        entity.setAnoPublicacao(dto.getAnoPublicacao());
        entity.setPreco(dto.getPreco());

        return entity;
    }

    private livroResponseDTO entityToResponseDTO(livro entity) {
        livroResponseDTO dto = new livroResponseDTO();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setAutor(entity.getAutor());
        dto.setCodigo(entity.getCodigo());
        dto.setAnoPublicacao(entity.getAnoPublicacao());
        dto.setPreco(entity.getPreco());

        return dto;
    }
}