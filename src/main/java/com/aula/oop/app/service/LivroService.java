package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.BusinessException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public LivroResponseDTO criar(LivroRequestDTO dto) {
        validarAno(dto.getAnoPublicacao());

        repository.findByCodigo(dto.getCodigo()).ifPresent(l -> {
            throw new BusinessException("Já existe um livro com esse código.");
        });

        Livro livro = new Livro();
        preencherEntidade(livro, dto);

        return toResponse(repository.save(livro));
    }

    public List<LivroResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = buscarEntidade(id);
        return toResponse(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        validarAno(dto.getAnoPublicacao());

        Livro livro = buscarEntidade(id);

        repository.findByCodigo(dto.getCodigo()).ifPresent(existente -> {
            if (!existente.getId().equals(id)) {
                throw new BusinessException("Já existe um livro com esse código.");
            }
        });

        preencherEntidade(livro, dto);

        return toResponse(repository.save(livro));
    }

    public void remover(Long id) {
        Livro livro = buscarEntidade(id);
        repository.delete(livro);
    }

    private Livro buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));
    }

    private void preencherEntidade(Livro livro, LivroRequestDTO dto) {
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
    }

    private LivroResponseDTO toResponse(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }

    private void validarAno(Integer ano) {
        int anoAtual = Year.now().getValue();

        if (ano > anoAtual) {
            throw new BusinessException("Ano de publicação não pode ser maior que o ano atual.");
        }
    }
}