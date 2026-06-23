package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.BusinessException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(LivroResponseDTO::deEntidade)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com o ID: " + id));
        return LivroResponseDTO.deEntidade(livro);
    }

    @Transactional
    public LivroResponseDTO salvar(LivroDTO dto) {
        if (repository.findByCodigo(dto.codigo()).isPresent()) {
            throw new BusinessException("Já existe um livro cadastrado com o código: " + dto.codigo());
        }

        Livro livro = new Livro(
                dto.titulo(),
                dto.autor(),
                dto.codigo(),
                dto.anoPublicacao(),
                dto.preco()
        );
        Livro livroSalvo = repository.save(livro);
        return LivroResponseDTO.deEntidade(livroSalvo);
    }

    @Transactional
    public LivroResponseDTO atualizar(Long id, LivroDTO dto) {
        Livro livroExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não é possível atualizar. Livro não encontrado com o ID: " + id));

        Optional<Livro> livroComMesmoCodigo = repository.findByCodigo(dto.codigo());
        if (livroComMesmoCodigo.isPresent() && !livroComMesmoCodigo.get().getId().equals(id)) {
            throw new BusinessException("Não é possível atualizar. O código '" + dto.codigo() + "' já está em uso por outro livro.");
        }

        livroExistente.setTitulo(dto.titulo());
        livroExistente.setAutor(dto.autor());
        livroExistente.setCodigo(dto.codigo());
        livroExistente.setAnoPublicacao(dto.anoPublicacao());
        livroExistente.setPreco(dto.preco());

        Livro livroAtualizado = repository.save(livroExistente);
        return LivroResponseDTO.deEntidade(livroAtualizado);
    }

    @Transactional
    public void excluir(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não é possível excluir. Livro não encontrado com o ID: " + id));
        repository.delete(livro);
    }
}