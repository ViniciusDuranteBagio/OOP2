package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.model.LivroModel;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<LivroResponseDTO> getAll() {
        return livroRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public LivroResponseDTO getById(Long id) {
        LivroModel livro = buscarOuFalhar(id);
        return entityToResponseDTO(livro);
    }

    public LivroResponseDTO createLivro(LivroRequestDTO livro) {
        if (livroRepository.existsByCodigo(livro.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Já existe um livro cadastrado com o código '" + livro.getCodigo() + "'");
        }

        LivroModel entity = requestDtoToEntity(livro);
        return entityToResponseDTO(livroRepository.save(entity));
    }

    public LivroResponseDTO updateLivro(Long id, LivroRequestDTO livro) {
        LivroModel livroModel = buscarOuFalhar(id);

        boolean codigoAlterado = !livroModel.getCodigo().equals(livro.getCodigo());
        if (codigoAlterado && livroRepository.existsByCodigo(livro.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Já existe um livro cadastrado com o código '" + livro.getCodigo() + "'");
        }

        livroModel.setTitulo(livro.getTitulo());
        livroModel.setAutor(livro.getAutor());
        livroModel.setCodigo(livro.getCodigo());
        livroModel.setAnoPublicacao(livro.getAnoPublicacao());
        livroModel.setPreco(livro.getPreco());

        return entityToResponseDTO(livroRepository.save(livroModel));
    }

    public void deleteLivro(Long id) {
        LivroModel livro = buscarOuFalhar(id);
        livroRepository.delete(livro);
    }

    private LivroModel buscarOuFalhar(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Livro com id " + id + " não encontrado"));
    }

    private LivroResponseDTO entityToResponseDTO(LivroModel entity) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setAutor(entity.getAutor());
        dto.setCodigo(entity.getCodigo());
        dto.setAnoPublicacao(entity.getAnoPublicacao());
        dto.setPreco(entity.getPreco());
        return dto;
    }

    private LivroModel requestDtoToEntity(LivroRequestDTO livro) {
        LivroModel entity = new LivroModel();
        entity.setTitulo(livro.getTitulo());
        entity.setAutor(livro.getAutor());
        entity.setCodigo(livro.getCodigo());
        entity.setAnoPublicacao(livro.getAnoPublicacao());
        entity.setPreco(livro.getPreco());
        return entity;
    }
}