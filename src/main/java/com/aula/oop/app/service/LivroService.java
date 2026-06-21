package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoJaCadastradoException;
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

    public LivroResponseDTO salvar(LivroRequestDTO requestDTO) {
        LivroDTO dto = converterRequestParaDTO(requestDTO);

        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoJaCadastradoException("Já existe um livro com esse código");
        }

        Livro livro = converterDTOParaEntity(dto);
        Livro livroSalvo = repository.save(livro);

        return converterParaResponseDTO(livroSalvo);
    }

    public List<LivroResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        return converterParaResponseDTO(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO requestDTO) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        LivroDTO dto = converterRequestParaDTO(requestDTO);

        if (!livro.getCodigo().equals(dto.getCodigo()) && repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoJaCadastradoException("Já existe um livro com esse código");
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        Livro livroAtualizado = repository.save(livro);
        return converterParaResponseDTO(livroAtualizado);
    }

    public void deletar(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

        repository.delete(livro);
    }

    private LivroDTO converterRequestParaDTO(LivroRequestDTO requestDTO) {
        return new LivroDTO(
                requestDTO.getTitulo(),
                requestDTO.getAutor(),
                requestDTO.getCodigo(),
                requestDTO.getAnoPublicacao(),
                requestDTO.getPreco()
        );
    }

    private Livro converterDTOParaEntity(LivroDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
        return livro;
    }

    private LivroResponseDTO converterParaResponseDTO(Livro livro) {
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