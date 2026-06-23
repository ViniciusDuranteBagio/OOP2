package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<LivroResponseDTO> listAll() {
        return livroService.getAll();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO getById(@PathVariable Long id) {
        return livroService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO createLivro(@RequestBody @Valid LivroRequestDTO livro) {
        return livroService.createLivro(livro);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO updateLivro(@PathVariable Long id, @RequestBody @Valid LivroRequestDTO livro) {
        return livroService.updateLivro(id, livro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLivro(@PathVariable Long id) {
        livroService.deleteLivro(id);
    }
}