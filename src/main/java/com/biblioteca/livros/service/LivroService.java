package com.biblioteca.livros.service;

import com.biblioteca.livros.dto.LivroRequestDTO;
import com.biblioteca.livros.dto.LivroResponseDTO;
import com.biblioteca.livros.entity.Livro;
import com.biblioteca.livros.exception.CodigoDuplicadoException;
import com.biblioteca.livros.exception.LivroNotFoundException;
import com.biblioteca.livros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {
        if (livroRepository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicadoException(dto.getCodigo());
        }

        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        Livro salvo = livroRepository.save(livro);
        return new LivroResponseDTO(salvo);
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(LivroResponseDTO::new)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));

        // Verifica se o novo código pertence a outro livro
        if (livroRepository.existsByCodigoAndIdNot(dto.getCodigo(), id)) {
            throw new CodigoDuplicadoException(dto.getCodigo());
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        Livro atualizado = livroRepository.save(livro);
        return new LivroResponseDTO(atualizado);
    }

    public void remover(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNotFoundException(id);
        }
        livroRepository.deleteById(id);
    }
}
