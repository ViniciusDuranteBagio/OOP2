package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping
    public LivroDTO cadastrar(@Valid @RequestBody LivroDTO dto) {
        return service.cadastrar(dto);
    }

    @GetMapping
    public List<LivroDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public LivroDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public LivroDTO atualizar(@PathVariable Long id, @Valid @RequestBody LivroDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}