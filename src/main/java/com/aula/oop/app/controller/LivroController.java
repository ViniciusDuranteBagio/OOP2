package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequest;
import com.aula.oop.app.dto.LivroResponse;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponse> cadastrar(@Valid @RequestBody LivroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequest request) {
        return ResponseEntity.ok(livroService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        livroService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
