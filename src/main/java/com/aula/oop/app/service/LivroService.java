package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
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

    public Livro cadastrar(Livro livro) {

        if(repository.existsByCodigo(livro.getCodigo())) {
            throw new CodigoDuplicadoException(
                    livro.getCodigo());
        }

        return repository.save(livro);
    }

    public Livro buscar(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new LivroNaoEncontradoException(id)
                );
    }

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Livro atualizar(Long id, Livro livroAtualizado) {

        Livro livro = buscar(id);

        livro.setTitulo(
                livroAtualizado.getTitulo());

        livro.setAutor(
                livroAtualizado.getAutor());

        livro.setCodigo(
                livroAtualizado.getCodigo());

        livro.setAnoPublicacao(
                livroAtualizado.getAnoPublicacao());

        livro.setPreco(
                livroAtualizado.getPreco());

        return repository.save(livro);
    }

    public void excluir(Long id) {

        Livro livro = buscar(id);

        repository.delete(livro);
    }

}
