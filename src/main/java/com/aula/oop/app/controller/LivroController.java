package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroDTO;
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
    public List<LivroResponseDTO> listarTodos() {
        return livroService.getAllLivros();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return livroService.getLivroById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO criar(@RequestBody @Valid LivroDTO dto) {
        return livroService.createLivro(dto);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid LivroRequestDTO dto) {
        return livroService.updateLivro(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        livroService.deleteLivro(id);
    }
}