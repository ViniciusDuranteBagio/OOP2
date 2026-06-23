package com.aula.oop.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

   
    public LivroResponseDTO salvar(LivroRequestDTO dto) {

        if (repository.existsByCodigo(dto.codigo())) {
            throw new CodigoDuplicadoException("Já existe um livro com esse código");
        }

        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setCodigo(dto.codigo());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setPreco(dto.preco());

        repository.save(livro);

        return converter(livro);
    }

    public List<LivroResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO buscar(Long id) {
        Livro livro = buscarEntidade(id);
        return converter(livro);
    }


    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {

        Livro livro = buscarEntidade(id);

        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setCodigo(dto.codigo());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setPreco(dto.preco());

        return converter(repository.save(livro));
    }

    public void deletar(Long id) {
        Livro livro = buscarEntidade(id);
        repository.delete(livro);
    }

    private Livro buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado"));
    }

    private LivroResponseDTO converter(Livro livro) {
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