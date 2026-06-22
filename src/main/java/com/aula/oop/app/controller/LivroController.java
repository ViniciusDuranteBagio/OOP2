package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroDTO;
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

    @PostMapping
    public Livro cadastrar(@RequestBody @Valid LivroDTO dto) {
        return service.cadastrar(dto);
    }

    @GetMapping
    public List<Livro> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id,
                           @RequestBody @Valid LivroDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}