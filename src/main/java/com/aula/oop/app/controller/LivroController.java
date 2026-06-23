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

        private final LivroService service;

        public LivroController(LivroService service) {
            this.service = service;
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public LivroResponseDTO cadastrar(
                @RequestBody @Valid LivroRequestDTO dto) {

            return service.cadastrar(dto);
        }

        @GetMapping
        public List<LivroResponseDTO> listar() {
            return service.listarTodos();
        }

        @GetMapping("/{id}")
        public LivroResponseDTO buscar(@PathVariable Long id) {
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
        public void remover(@PathVariable Long id) {
            service.remover(id);
        }
    }

