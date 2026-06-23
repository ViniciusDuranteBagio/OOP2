package com.aula.oop.app.service;




import com.aula.oop.app.model.Livro;
import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicadoException;
import com.aula.oop.app.exceptions.LivroNaoEncontradoException;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

            Livro livro = new Livro();

            livro.setTitulo(dto.getTitulo());
            livro.setAutor(dto.getAutor());
            livro.setCodigo(dto.getCodigo());
            livro.setAnoPublicacao(dto.getAnoPublicacao());
            livro.setPreco(dto.getPreco());

            Livro livroSalvo = repository.save(livro);

            return converterParaDTO(livroSalvo);
        }

        public List<LivroResponseDTO> listarTodos() {

            return repository.findAll()
                    .stream()
                    .map(this::converterParaDTO)
                    .toList();
        }

        public LivroResponseDTO buscarPorId(Long id) {

            Livro livro = repository.findById(id)
                    .orElseThrow(() -> new LivroNaoEncontradoException(id));

            return converterParaDTO(livro);
        }

        public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {

            Livro livro = repository.findById(id)
                    .orElseThrow(() -> new LivroNaoEncontradoException(id));

            if (!livro.getCodigo().equals(dto.getCodigo())
                    && repository.existsByCodigo(dto.getCodigo())) {

                throw new CodigoDuplicadoException(dto.getCodigo());
            }

            livro.setTitulo(dto.getTitulo());
            livro.setAutor(dto.getAutor());
            livro.setCodigo(dto.getCodigo());
            livro.setAnoPublicacao(dto.getAnoPublicacao());
            livro.setPreco(dto.getPreco());

            Livro atualizado = repository.save(livro);

            return converterParaDTO(atualizado);
        }

        public void remover(Long id) {

            Livro livro = repository.findById(id)
                    .orElseThrow(() -> new LivroNaoEncontradoException(id));

            repository.delete(livro);
        }

        private LivroResponseDTO converterParaDTO(Livro livro) {

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

