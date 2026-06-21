package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
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

    public List<LivroResponseDTO> getAllLivros() {
        return livroRepository.findAll().stream().map(this::convertEntityToResponseDTO).toList();
    }

    public LivroResponseDTO getLivroById(Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException(id));
        return convertEntityToResponseDTO(livro);
    }

    public LivroResponseDTO createLivro(LivroDTO dto) {
        livroRepository.findByCodigo(dto.getCodigo()).ifPresent(l -> { throw new CodigoDuplicadoException(dto.getCodigo()); });

        Livro entity = convertDTOToEntity(dto);
        Livro salvo = livroRepository.save(entity);
        return convertEntityToResponseDTO(salvo);
    }

    public LivroResponseDTO updateLivro(Long id, LivroRequestDTO dto) {
        Livro existente = livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException(id));

        livroRepository.findByCodigo(dto.getCodigo()).filter(l -> !l.getId().equals(id)).ifPresent(l -> { throw new CodigoDuplicadoException(dto.getCodigo()); });

        existente.setTitulo(dto.getTitulo());
        existente.setAutor(dto.getAutor());
        existente.setCodigo(dto.getCodigo());
        existente.setAnoPublicacao(dto.getAnoPublicacao());
        existente.setPreco(dto.getPreco());

        Livro atualizado = livroRepository.save(existente);
        return convertEntityToResponseDTO(atualizado);
    }

    public void deleteLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNaoEncontradoException(id);
        }
        livroRepository.deleteById(id);
    }

    private Livro convertDTOToEntity(LivroDTO dto) {
        Livro entity = new Livro();
        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        entity.setCodigo(dto.getCodigo());
        entity.setPreco(dto.getPreco());
        entity.setAnoPublicacao(dto.getAnoPublicacao());
        return entity;
    }

    private LivroResponseDTO convertEntityToResponseDTO(Livro entity) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(entity.getId());
        dto.setAnoPublicacao(entity.getAnoPublicacao());
        dto.setTitulo(entity.getTitulo());
        dto.setAutor(entity.getAutor());
        dto.setPreco(entity.getPreco());
        dto.setCodigo(entity.getCodigo());
        return dto;
    }
}