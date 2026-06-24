package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public LivroResponseDTO criar(@RequestBody Livro livro) {
        return service.criar(livro);
    }

    @GetMapping
    public List<LivroResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(@PathVariable Long id,
                                      @RequestBody Livro livro) {
        return service.atualizar(id, livro);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}