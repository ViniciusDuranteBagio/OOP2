package com.aula.oop.trabalhofinal.service;

import com.aula.oop.trabalhofinal.dto.LivroRequestDTO;
import com.aula.oop.trabalhofinal.dto.LivroResponseDTO;
import com.aula.oop.trabalhofinal.entity.Livro;
import com.aula.oop.trabalhofinal.exceptions.CodigoDuplicadoException;
import com.aula.oop.trabalhofinal.exceptions.LivroNaoEncontradoException;
import com.aula.oop.trabalhofinal.repository.LivroRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public LivroResponseDTO salvar(LivroRequestDTO dto) {

        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicadoException(dto.getCodigo());
        }

        Livro livro = new Livro();

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        livro = repository.save(livro);

        return converterParaDTO(livro);
    }

    public List<LivroResponseDTO> listarTodos() {

        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException(id));

        return converterParaDTO(livro);
    }

    public LivroResponseDTO atualizar(Long id,
                                      LivroRequestDTO dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException(id));

        if (!livro.getCodigo().equals(dto.getCodigo())
                && repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicadoException(dto.getCodigo());
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        repository.save(livro);

        return converterParaDTO(livro);
    }

    public void deletar(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException(id));

        repository.delete(livro);
    }

    private LivroResponseDTO converterParaDTO(Livro livro) {

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