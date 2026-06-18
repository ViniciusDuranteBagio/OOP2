package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    // POST /livros - Cadastrar um novo livro
    @PostMapping
    public ResponseEntity<LivroDTO> cadastrar(@Valid @RequestBody LivroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    // GET /livros - Listar todos os livros
    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /livros/{id} - Buscar um livro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /livros/{id} - Atualizar os dados de um livro
    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LivroDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    // DELETE /livros/{id} - Remover um livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
