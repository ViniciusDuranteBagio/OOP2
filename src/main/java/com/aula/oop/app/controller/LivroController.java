package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> cadastrar(@Valid @RequestBody LivroRequestDTO dto) {
        LivroResponseDTO livro = livroService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listarTodos() {
        List<LivroResponseDTO> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        LivroResponseDTO livro = livroService.buscarPorId(id);
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LivroRequestDTO dto
    ) {
        LivroResponseDTO livro = livroService.atualizar(id, dto);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        livroService.remover(id);
        return ResponseEntity.noContent().build();
    }
}