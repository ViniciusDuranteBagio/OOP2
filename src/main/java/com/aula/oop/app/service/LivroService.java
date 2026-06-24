package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.LivroExistenteComEsseCodigo;
import com.aula.oop.app.model.entity.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;

import java.util.List;

@Service
public class LivroService {
    LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    //cadastrar um livro novo

    public LivroResponseDTO cadastrarLivro(LivroRequestDTO livro) {

        //foi usado um if pois a resposta nao se encaixa para ser usado no optional, ja que ele nao possui um metodo de
        // findbycodigo, nesse foi criado daria para criar um optional no repository ou um boolean, optei pelo boolean mesmo
        // para retornar true ou false ao inves de um obj
        //verifica se existe um livro com esse id, atraves do metodo criado no repository, e se tiver lança a exeption
        if (livroRepository.existsByCodigo(livro.getCodigo())) {
            throw new LivroExistenteComEsseCodigo(livro.getCodigo());
        }
        Livro entity = dtoToEntity(livro);
        return entityToResponseDTO(livroRepository.save(entity));
    }

    //deletar livro

    public void deletarLivro (Long id ){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        livroRepository.deleteById(id);
    }

    //buscar livro pelo id

    public LivroResponseDTO buscarPorId(Long id) {

        //aqui também a mesma coisa, nao foi criado um metodo no repository pois ja existe o
        //findbyid no jpa (pelo o que eu pesquisei)
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        return entityToResponseDTO(livro);
    }

    //listar todos os livros
    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    //atualizar dados livro

    public LivroResponseDTO atualizarLivro(Long id, LivroRequestDTO livroNovo) {
        Livro livroParaAtualizar = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
        livroParaAtualizar.setTitulo(livroNovo.getTitulo());
        livroParaAtualizar.setAutor(livroNovo.getAutor());
        livroParaAtualizar.setCodigo(livroNovo.getCodigo());
        livroParaAtualizar.setAnoPublicacao(livroNovo.getAnoPublicacao());
        livroParaAtualizar.setPreco(livroNovo.getPreco());

        livroRepository.save(livroParaAtualizar);

        return entityToResponseDTO(livroParaAtualizar);
    }

    //converter dto para entidade

    public Livro dtoToEntity(LivroRequestDTO dto) {
        Livro entity = new Livro();
        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        entity.setCodigo(dto.getCodigo());
        entity.setAnoPublicacao(dto.getAnoPublicacao());
        entity.setPreco(dto.getPreco());
        return entity;
    }

    //converter entidade para dto

    public LivroResponseDTO entityToResponseDTO(Livro entity) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setAutor(entity.getAutor());
        dto.setCodigo(entity.getCodigo());
        dto.setAnoPublicacao(entity.getAnoPublicacao());
        dto.setPreco(entity.getPreco());
        return dto;
    }
}

