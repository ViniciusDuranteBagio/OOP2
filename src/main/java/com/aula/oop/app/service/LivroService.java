package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.BusinessException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {
        if (livroRepository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException(
                    "Já existe um livro cadastrado com o código '" + dto.getCodigo() + "'"
            );
        }

        Livro livro = Livro.builder()
                .titulo(dto.getTitulo())
                .autor(dto.getAutor())
                .codigo(dto.getCodigo())
                .anoPublicacao(dto.getAnoPublicacao())
                .preco(dto.getPreco())
                .build();

        Livro livroSalvo = livroRepository.save(livro);
        return toResponseDTO(livroSalvo);
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livro com id " + id + " não foi encontrado!"
                ));
        return toResponseDTO(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livro com id " + id + " não foi encontrado!"
                ));

        boolean codigoMudou = !livroExistente.getCodigo().equals(dto.getCodigo());
        if (codigoMudou && livroRepository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException(
                    "Ja existe um livro cadastrado com o código '" + dto.getCodigo() + "'"
            );
        }

        livroExistente.setTitulo(dto.getTitulo());
        livroExistente.setAutor(dto.getAutor());
        livroExistente.setCodigo(dto.getCodigo());
        livroExistente.setAnoPublicacao(dto.getAnoPublicacao());
        livroExistente.setPreco(dto.getPreco());

        Livro livroAtualizado = livroRepository.save(livroExistente);
        return toResponseDTO(livroAtualizado);
    }

    public void remover(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Livro com id " + id + " não foi encontrado!"
            );
        }
        livroRepository.deleteById(id);
    }

    private LivroResponseDTO toResponseDTO(Livro livro) {
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .codigo(livro.getCodigo())
                .anoPublicacao(livro.getAnoPublicacao())
                .preco(livro.getPreco())
                .build();
    }

}