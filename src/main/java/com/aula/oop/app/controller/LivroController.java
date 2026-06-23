package com.aula.oop.app.controller;


import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.service.LivroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    public LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<LivroResponseDTO> listarTodos() {
        return livroService.listarTodos();
    }


    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @PostMapping
    public LivroResponseDTO cadastrar(@RequestBody @Valid LivroRequestDTO dto) {
        return livroService.cadastrar(dto);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid LivroRequestDTO dto) {
        return livroService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        livroService.remover(id);
    }

}