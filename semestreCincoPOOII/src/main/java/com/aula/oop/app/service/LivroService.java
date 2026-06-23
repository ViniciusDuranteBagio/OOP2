package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivrosDTO;
import com.aula.oop.app.dto.LivrosRequestDTO;
import com.aula.oop.app.dto.LivrosResponseDTO;
import com.aula.oop.app.exceptions.CodigoJaCadastradoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livros;
import com.aula.oop.app.repository.LivrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivrosRepository repository;

    public LivroService(LivrosRepository repository) {
        this.repository = repository;
    }

    public LivrosResponseDTO salvar(LivrosRequestDTO requestDTO) {
        LivrosDTO dto = converterRequestParaDTO(requestDTO);

        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoJaCadastradoException(dto.getCodigo());
        }

        Livros livro = converterDTOParaEntity(dto);
        Livros livroSalvo = repository.save(livro);

        return converterParaResponseDTO(livroSalvo);
    }

    public List<LivrosResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public LivrosResponseDTO buscarPorId(Long id) {
        Livros livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        return converterParaResponseDTO(livro);
    }

    public LivrosResponseDTO atualizar(Long id, LivrosRequestDTO requestDTO) {
        Livros livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        LivrosDTO dto = converterRequestParaDTO(requestDTO);

        if (!livro.getCodigo().equals(dto.getCodigo()) && repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoJaCadastradoException(dto.getCodigo());
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        Livros livroAtualizado = repository.save(livro);
        return converterParaResponseDTO(livroAtualizado);
    }

    public void deletar(Long id) {
        Livros livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        repository.delete(livro);
    }

    private LivrosDTO converterRequestParaDTO(LivrosRequestDTO requestDTO) {
        return new LivrosDTO(
                requestDTO.getTitulo(),
                requestDTO.getAutor(),
                requestDTO.getCodigo(),
                requestDTO.getAnoPublicacao(),
                requestDTO.getPreco()
        );
    }

    private Livros converterDTOParaEntity(LivrosDTO dto) {
        Livros livro = new Livros();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
        return livro;
    }

    private LivrosResponseDTO converterParaResponseDTO(Livros livro) {
        return new LivrosResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }
}