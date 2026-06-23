package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {
        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicadoException(dto.getCodigo());
        }
        Livro livro = new Livro(dto.getTitulo(), dto.getAutor(), dto.getCodigo(),
                dto.getAnoPublicacao(), dto.getPreco());
        return toDTO(repository.save(livro));
    }

    public List<LivroResponseDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
        return toDTO(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        Optional<Livro> porCodigo = repository.findByCodigo(dto.getCodigo());
        if (porCodigo.isPresent() && !porCodigo.get().getId().equals(id)) {
            throw new CodigoDuplicadoException(dto.getCodigo());
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
        return toDTO(repository.save(livro));
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) throw new LivroNaoEncontradoException(id);
        repository.deleteById(id);
    }

    private LivroResponseDTO toDTO(Livro l) {
        return new LivroResponseDTO(l.getId(), l.getTitulo(), l.getAutor(),
                l.getCodigo(), l.getAnoPublicacao(), l.getPreco());
    }
}