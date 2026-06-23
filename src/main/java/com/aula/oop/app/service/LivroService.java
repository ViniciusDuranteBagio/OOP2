package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class LivroService {
    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;

    }

    public LivroResponseDTO salvar(LivroRequestDTO dto){

        if(livroRepository.findByCodigo(dto.getCodigo()).isPresent()){
            throw new CodigoDuplicadoException(
                    "Código já cadastrado");
        }

        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());


        Livro salvo = livroRepository.save(livro);
        return entityTOresponseDTO(salvo);
    }

    public LivroResponseDTO buscarPorId(Long id){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Livro não encontrado"
                )
        );

        return entityTOresponseDTO(livro);

    }
    public List<LivroResponseDTO> buscarTodosLivros(){


        List<Livro> livros = livroRepository.findAll();


        return livros.stream()
                .map(this::entityTOresponseDTO)
                .toList();

    }

    public LivroResponseDTO AtualizarLivro(LivroRequestDTO dto,Long id){
        Livro livro = livroRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Livro não encontrado"
                        )
                );


        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        Livro atualizado = livroRepository.save(livro);


        return entityTOresponseDTO(atualizado);

    }

    public void deletarLivro(Long id){


        Livro livro = livroRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Livro não encontrado"
                        )
                );


        livroRepository.delete(livro);

    }

    private Livro dtoTOentity(LivroDTO dto){
        Livro entity = new Livro();

        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        entity.setCodigo(dto.getCodigo());
        entity.setAnoPublicacao(dto.getAnoPublicacao());

        return entity;


    }

    private LivroResponseDTO entityTOresponseDTO(Livro entity){
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
