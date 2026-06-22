package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequest;
import com.aula.oop.app.dto.LivroResponse;
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
    public LivroResponse salvar(
            @RequestBody @Valid LivroRequest dto) {

        return service.salvar(dto);
    }

    @GetMapping
    public List<LivroResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public LivroResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequest dto) {

        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}