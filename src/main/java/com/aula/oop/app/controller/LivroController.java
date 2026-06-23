package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    // metodo para converter DTO → Entidade
    private Livro converterParaEntidade(LivroRequestDTO dto) {

        Livro livro = new Livro();

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());

        return livro;
    }

    // Conversão para regra de "A entidade não deve ser exposta diretamente nas requisições e respostas da API"
    // ao invés de mudar o "Livro" no Service por "LivroResponseDTO"

    // metodo para converter Entidade → DTO
    private LivroResponseDTO converterParaDTO(Livro livro) {

        LivroResponseDTO dto = new LivroResponseDTO();

        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setCodigo(livro.getCodigo());
        dto.setAnoPublicacao(livro.getAnoPublicacao());
        dto.setPreco(livro.getPreco());

        return dto;
    }

    // Endpoint POST /livros * O @Valid faz as validações do DTO funcionarem automaticamente
    @PostMapping
    public LivroResponseDTO cadastrar(
            @Valid @RequestBody LivroRequestDTO dto) {

        Livro livro = converterParaEntidade(dto);

        Livro salvo = service.cadastrar(livro);

        return converterParaDTO(salvo);
    }

    // Endpoint GET /livros/{id} (específico)
    @GetMapping("/{id}")
    public LivroResponseDTO buscar(@PathVariable Long id) {

        Livro livro = service.buscar(id);

        return converterParaDTO(livro);
    }

    // Endpoint GET /livros (todos)
    @GetMapping
    public List<LivroResponseDTO> listarTodos() {

        return service.listarTodos()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // Endpoint PUT /livros/{id}
    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LivroRequestDTO dto) {

        Livro livro = converterParaEntidade(dto);

        Livro atualizado = service.atualizar(id, livro);

        return converterParaDTO(atualizado);
    }

    // Endpoint DELETE /livros/{id}
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        service.excluir(id);
    }
}
