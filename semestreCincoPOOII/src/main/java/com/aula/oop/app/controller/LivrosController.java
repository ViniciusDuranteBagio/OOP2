package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivrosRequestDTO;
import com.aula.oop.app.dto.LivrosResponseDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final LivroService livroService;

    public LivrosController(final LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public LivrosResponseDTO salvar(@RequestBody @Valid final LivrosRequestDTO requestDTO) {
        return livroService.salvar(requestDTO);
    }

    @GetMapping
    public List<LivrosResponseDTO> listarTodos() {
        return livroService.listarTodos();
    }

    @GetMapping("/{id}")
    public LivrosResponseDTO buscarPorId(@PathVariable("id") final Long id) {
        return livroService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LivrosResponseDTO atualizar(@PathVariable("id") final Long id,
                                      @RequestBody @Valid final LivrosRequestDTO requestDTO) {
        return livroService.atualizar(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") final Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build(); // 204, sucesso mas sem corpo
    }
}