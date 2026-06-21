package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.LivroModel;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO createLivro(LivroRequestDTO dto) {
        boolean existe = livroRepository.existsByCodigo(dto.getCodigo());

        if (existe) {
            throw new CodigoDuplicadoException("Já existe um livro cadastrado com este código.");
        }

        LivroModel entity = dtoToEntity(dto);
        return entityToResponseDTO(livroRepository.save(entity));
    }

    public List<LivroResponseDTO> getAllLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public LivroResponseDTO updateLivro(Long id, LivroRequestDTO dto) {

        LivroModel livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        boolean existe = livroRepository.existsByCodigoAndIdNot(dto.getCodigo(), id);

        if (existe) {
            throw new CodigoDuplicadoException("Já existe um livro cadastrado com este código.");
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        return entityToResponseDTO(livroRepository.save(livro));
    }

    public void deleteLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNaoEncontradoException("Livro não encontrado");
        }
        livroRepository.deleteById(id);
    }

    public LivroResponseDTO getLivroById(Long id) {
        LivroModel livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        return entityToResponseDTO(livro);
    }

    private LivroModel dtoToEntity(LivroRequestDTO dto) {
        LivroModel livro = new LivroModel();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
        return livro;
    }

    private LivroResponseDTO entityToResponseDTO(LivroModel livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }
}