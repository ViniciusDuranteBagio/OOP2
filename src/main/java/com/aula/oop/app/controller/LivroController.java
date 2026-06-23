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

    public LivroController(final LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public LivroResponseDTO salvar(@RequestBody @Valid final LivroRequestDTO requestDTO) {
        return livroService.salvar(requestDTO);
    }

    @GetMapping
    public List<LivroResponseDTO> listarTodos() {
        return livroService.listarTodos();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable("id") final Long id) {
        return livroService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(@PathVariable("id") final Long id,
                                      @RequestBody @Valid final LivroRequestDTO requestDTO) {
        return livroService.atualizar(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") final Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build(); // 204, sucesso mas sem corpo
    }
}