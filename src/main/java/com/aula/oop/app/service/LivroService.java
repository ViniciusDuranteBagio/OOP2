package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroDTO;

import com.aula.oop.app.livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    // 1. CADASTRAR UM NOVO LIVRO
    public LivroDTO cadastrar(LivroDTO dto) {
        // Regra de negócio: Não permite códigos iguais
        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException("Já existe um livro cadastrado com este código!");
        }

        // Transforma o DTO em Livro (entidade) para salvar no banco
        livro novoLivro = new livro(null, dto.getTitulo(), dto.getAutor(), dto.getCodigo(), dto.getAnoPublicacao(), dto.getPreco());
        return new LivroDTO(repository.save(novoLivro));
    }

    // 2. LISTAR TODOS OS LIVROS
    public List<LivroDTO> listarTodos() {
        return repository.findAll().stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());
    }

    // 3. BUSCAR UM LIVRO PELO ID
    public LivroDTO buscarPorId(Long id) {
        // Regra de negócio: Retorna erro se o ID não existir
        livro livroEncontrado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));
        return new LivroDTO(livroEncontrado);
    }

    // 4. ATUALIZAR UM LIVRO
    public LivroDTO atualizar(Long id, LivroDTO dto) {
        // Regra de negócio: Verifica se o livro existe antes de alterar
        livro livroExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não é possível atualizar. Livro não encontrado!"));

        // Regra de negócio: Não permite atualizar para um código que outro livro já usa
        if (repository.existsByCodigoAndIdNot(dto.getCodigo(), id)) {
            throw new RuntimeException("Este código já está a ser utilizado por outro livro!");
        }

        livroExistente.setTitulo(dto.getTitulo());
        livroExistente.setAutor(dto.getAutor());
        livroExistente.setCodigo(dto.getCodigo());
        livroExistente.setAnoPublicacao(dto.getAnoPublicacao());
        livroExistente.setPreco(dto.getPreco());

        return new LivroDTO(repository.save(livroExistente));
    }

    // 5. REMOVER UM LIVRO
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível remover. Livro não encontrado!");
        }
        repository.deleteById(id);
    }
}
