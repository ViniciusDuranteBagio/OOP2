package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.model.Livros;
import com.aula.oop.app.service.LivroService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Livros>> listarTodos() {

        return ResponseEntity.ok(
                service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livros> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Livros> criar(
            @Valid
            @RequestBody LivroRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livros> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LivroRequestDTO dto) {

        return ResponseEntity.ok(
                service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
