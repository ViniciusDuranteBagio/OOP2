package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.exceptions.BusinessException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
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

    public Livro salvar(LivroDTO dto) {

        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException("Já existe um livro com esse código");
        }

        Livro livro = new Livro();
        mapear(dto, livro);

        return repository.save(livro);
    }

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
    }

    public Livro atualizar(Long id, LivroDTO dto) {

        Livro livro = buscarPorId(id);

        if (!livro.getCodigo().equals(dto.getCodigo()) &&
                repository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException("Código já em uso");
        }

        mapear(dto, livro);

        return repository.save(livro);
    }

    public void deletar(Long id) {
        Livro livro = buscarPorId(id);
        repository.delete(livro);
    }

    private void mapear(LivroDTO dto, Livro livro) {
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
    }
}