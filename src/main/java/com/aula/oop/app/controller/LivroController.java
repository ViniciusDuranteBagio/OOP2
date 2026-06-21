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
@RequestMapping("api/livro")
public class LivroController {
    LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/listar")
    public List<LivroResponseDTO> listAll() {
        return livroService.getAllLivros();
    }

    @GetMapping("/listar/{id}")
    public LivroResponseDTO listById(@PathVariable Long id) {
        return livroService.getLivroById(id);
    }

    @PostMapping("/criar")
    public ResponseEntity<LivroResponseDTO> createLivro(@RequestBody @Valid LivroRequestDTO livro) {
        LivroResponseDTO response = livroService.createLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public LivroResponseDTO updateLivro(@PathVariable Long id, @RequestBody @Valid LivroRequestDTO livro) {
        return livroService.updateLivro(id, livro);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        livroService.deleteLivro(id);
        return ResponseEntity.noContent().build();
    }
}