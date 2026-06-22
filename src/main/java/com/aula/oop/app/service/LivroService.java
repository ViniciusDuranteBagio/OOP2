package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequest;
import com.aula.oop.app.dto.LivroResponse;
import com.aula.oop.app.exceptions.CodigoDuplicado;
import com.aula.oop.app.exceptions.LivroNaoEncontrado;
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

    public LivroResponse salvar(LivroRequest dto) {
        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicado("Ja existe um livro cadastrado com este codigo.");
        }

        Livro livro = new Livro();
        copiarDados(dto, livro);

        return converter(repository.save(livro));
    }

    public List<LivroResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public LivroResponse buscar(Long id) {
        Livro livro = buscarEntidadePorId(id);
        return converter(livro);
    }

    public LivroResponse atualizar(Long id, LivroRequest dto) {
        Livro livro = buscarEntidadePorId(id);

        if (repository.existsByCodigoAndIdNot(dto.getCodigo(), id)) {
            throw new CodigoDuplicado("Ja existe outro livro cadastrado com este codigo.");
        }

        copiarDados(dto, livro);

        return converter(repository.save(livro));
    }

    public void deletar(Long id) {
        Livro livro = buscarEntidadePorId(id);
        repository.delete(livro);
    }

    private Livro buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontrado("Livro nao encontrado."));
    }

    private void copiarDados(LivroRequest dto, Livro livro) {
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
    }

    private LivroResponse converter(Livro livro) {
        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }
}
