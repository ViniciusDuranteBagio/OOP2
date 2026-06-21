package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public LivroResponseDTO salvar(@RequestBody @Valid LivroRequestDTO requestDTO) {
        return livroService.salvar(requestDTO);
    }

    @GetMapping
    public List<LivroResponseDTO> listarTodos() {
        return livroService.listarTodos();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(@PathVariable Long id,
                                      @RequestBody @Valid LivroRequestDTO requestDTO) {
        return livroService.atualizar(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}