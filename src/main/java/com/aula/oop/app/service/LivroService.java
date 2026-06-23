package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        copiarDadosParaEntidade(dto, livro);

        Livro livroSalvo = livroRepository.save(livro);
        return converterParaResponseDTO(livroSalvo);
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll().stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = buscarEntidadePorId(id);
        return converterParaResponseDTO(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livroExistente = buscarEntidadePorId(id);

        boolean codigoAlterado = !livroExistente.getCodigo().equals(dto.getCodigo());
        if (codigoAlterado && livroRepository.existsByCodigo(dto.getCodigo())) {
            throw new CodigoDuplicadoException(dto.getCodigo());
        }

        copiarDadosParaEntidade(dto, livroExistente);

        Livro livroAtualizado = livroRepository.save(livroExistente);
        return converterParaResponseDTO(livroAtualizado);
    }

    public void remover(Long id) {
        Livro livro = buscarEntidadePorId(id);
        livroRepository.delete(livro);
    }

    // ------------------- Metodos auxiliares -------------------

    private Livro buscarEntidadePorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
    }

    private void copiarDadosParaEntidade(LivroRequestDTO dto, Livro livro) {
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
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