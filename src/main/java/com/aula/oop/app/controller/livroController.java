package com.aula.oop.app.controller;

import com.aula.oop.app.dto.livroRequestDTO;
import com.aula.oop.app.dto.livroResponseDTO;
import com.aula.oop.app.service.livroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class livroController {

    livroService livroService;

    public livroController(livroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public livroResponseDTO createTask(@RequestBody @Valid livroRequestDTO livro) {
        return livroService.createTask(livro);
    }

    @GetMapping
    public List<livroResponseDTO> listAll() {
        return livroService.getAllLivros();
    }

    @GetMapping("/{id}")
    public livroResponseDTO getById(@PathVariable Long id) {
        return livroService.getById(id);
    }

    @PutMapping("/{id}")
    public livroResponseDTO updateTask(@PathVariable Long id, @RequestBody @Valid livroRequestDTO livro) {
        return livroService.updateTask(id, livro);
    }

    @DeleteMapping("/{id}")
    public void deleteLivro(@PathVariable Long id) {
        livroService.deleteTask(id);
    }
}
