package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequest;
import com.aula.oop.app.dto.LivroResponse;
import com.aula.oop.app.exceptions.CodigoLivroDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponse cadastrar(LivroRequest request) {
        if (livroRepository.existsByCodigo(request.getCodigo())) {
            throw new CodigoLivroDuplicadoException(request.getCodigo());
        }

        Livro livro = new Livro();
        preencherLivro(livro, request);

        return converterParaResponse(livroRepository.save(livro));
    }

    public List<LivroResponse> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public LivroResponse buscarPorId(Long id) {
        return converterParaResponse(buscarEntidadePorId(id));
    }

    public LivroResponse atualizar(Long id, LivroRequest request) {
        Livro livro = buscarEntidadePorId(id);

        if (livroRepository.existsByCodigoAndIdNot(request.getCodigo(), id)) {
            throw new CodigoLivroDuplicadoException(request.getCodigo());
        }

        preencherLivro(livro, request);
        return converterParaResponse(livroRepository.save(livro));
    }

    public void remover(Long id) {
        Livro livro = buscarEntidadePorId(id);
        livroRepository.delete(livro);
    }

    private Livro buscarEntidadePorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
    }

    private void preencherLivro(Livro livro, LivroRequest request) {
        livro.setTitulo(request.getTitulo());
        livro.setAutor(request.getAutor());
        livro.setCodigo(request.getCodigo());
        livro.setAnoPublicacao(request.getAnoPublicacao());
        livro.setPreco(request.getPreco());
    }

    private LivroResponse converterParaResponse(Livro livro) {
        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoPublicacao(),
                livro.getPreco()
        );
    }
}
