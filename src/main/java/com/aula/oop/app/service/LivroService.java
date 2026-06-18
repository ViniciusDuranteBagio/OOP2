package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public Livro getLivro(Long id) {
        return livroRepository.findById(id).orElseThrow();
    }

    public Livro create(Livro entity) {
        livroRepository.save(entity);
        return entity;
    }

    public Livro convertDTOToEntity (LivroDTO dto) {
        Livro entity = new Livro();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setPreco(dto.getPreco());
        entity.setCodigo(dto.getCodigo());
        entity.setAutor(dto.getAutor());
        entity.setAnoPublicacao(dto.getAnoPublicacao());
        return entity;
    }

    public LivroResponseDTO convertEntityToResponseDTO (Livro entity) {
        LivroResponseDTO dto = new LivroResponseDTO();
        entity.setId(entity.getId());
        entity.setTitulo(entity.getTitulo());
        entity.setPreco(entity.getPreco());
        entity.setCodigo(entity.getCodigo());
        entity.setAutor(entity.getAutor());
        entity.setAnoPublicacao(entity.getAnoPublicacao());
        return dto;
    }

    public void delete(Long id) {

    }
    public Livro put(Long id, LivroDTO livro) {
        Livro livroToPut = livroRepository.findById(id).orElseThrow();
        return livroRepository.save(livroToPut);
    }

}
