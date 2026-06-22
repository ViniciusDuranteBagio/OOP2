package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroService {

    LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public Livro getLivro(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Livro não encontrado"));
    }

    public Livro create(Livro entity) {
        entity.setId(null);
        livroRepository.save(entity);
        return entity;
    }

    public Livro convertDTOToEntity(LivroDTO dto) {
        Livro entity = new Livro();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setPreco(dto.getPreco());
        entity.setCodigo(dto.getCodigo());
        entity.setAutor(dto.getAutor());
        entity.setAnoPublicacao(dto.getAnoPublicacao());
        return entity;
    }

    public LivroResponseDTO convertEntityToResponseDTO(Livro entity) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setPreco(entity.getPreco());
        dto.setCodigo(entity.getCodigo());
        dto.setAutor(entity.getAutor());
        dto.setAnoPublicacao(entity.getAnoPublicacao());
        return dto;
    }

    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro put(Long id, LivroDTO livroDTO) {
        Livro livroToPut = livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Livro não encontrado"));
        livroToPut.setTitulo(livroDTO.getTitulo());
        livroToPut.setPreco(livroDTO.getPreco());
        livroToPut.setCodigo(livroDTO.getCodigo());
        livroToPut.setAutor(livroDTO.getAutor());
        livroToPut.setAnoPublicacao(livroDTO.getAnoPublicacao());
        return livroRepository.save(livroToPut);
    }
}