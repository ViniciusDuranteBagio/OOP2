package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro com o ID " + id + " não foi encontrado no sistema."));
    }

    public Livro cadastrar(LivroDTO dto) {
        //n deixa cadastrar dois livros c msm código
        if (repository.findByCodigo(dto.getCodigo()).isPresent()) {
            throw new CodigoDuplicadoException("Não foi possível cadastrar. Já existe um livro com o código '" + dto.getCodigo() + "'.");
        }

        Livro livro = new Livro();
        copiarDtoParaEntidade(dto, livro);
        return repository.save(livro);
    }

    public Livro atualizar(Long id, LivroDTO dto) {
        //confere se o livro existe antes de alterar
        Livro livro = buscarPorId(id);

        //código alterado n pode ser igual ao de outro livro
        if (!livro.getCodigo().equals(dto.getCodigo()) && repository.findByCodigo(dto.getCodigo()).isPresent()) {
            throw new CodigoDuplicadoException("Não é possível alterar para o código '" + dto.getCodigo() + "' pois ele já está sendo usado por outro livro.");
        }

        copiarDtoParaEntidade(dto, livro);
        return repository.save(livro);
    }

    public void deletar(Long id) {
        //confere se o livro existe antes de deletar
        Livro livro = buscarPorId(id);
        repository.delete(livro);
    }

    //manda os dados do DTO p banco
    private void copiarDtoParaEntidade(LivroDTO dto, Livro entidade) {
        entidade.setTitulo(dto.getTitulo());
        entidade.setAutor(dto.getAutor());
        entidade.setCodigo(dto.getCodigo());
        entidade.setAnoPublicacao(dto.getAnoPublicacao());
        entidade.setPreco(dto.getPreco());
    }
}