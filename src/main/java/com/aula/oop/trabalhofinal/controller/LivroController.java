package com.aula.oop.trabalhofinal.controller;


import com.aula.oop.trabalhofinal.dto.LivroRequestDTO;


import com.aula.oop.trabalhofinal.dto.LivroResponseDTO;
import com.aula.oop.trabalhofinal.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO salvar(
            @RequestBody @Valid LivroRequestDTO dto) {

        return service.salvar(dto);
    }

    @GetMapping
    public List<LivroResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequestDTO dto) {

        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}