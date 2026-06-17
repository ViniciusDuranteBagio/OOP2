package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    LivroService livroService;

    public LivroController (LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public LivroResponseDTO createTask(@RequestBody @Valid LivroDTO livro) {
        return livroService.createTask(livro);
    }

    @GetMapping
    public List<LivroResponseDTO> listAll() {
        return livroService.getAllTasks();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO getById(@PathVariable Long id) {
        return livroService.getById(id);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO updateTask(@PathVariable Long id, @RequestBody @Valid LivroDTO livro) {
        return livroService.updateTask(id, livro);
    }

    @DeleteMapping("/{id}")
    public void deleteLivro(@PathVariable Long id) {
        livroService.deleteTask(id);
    }





}
