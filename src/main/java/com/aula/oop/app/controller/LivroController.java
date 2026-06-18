package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    public LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listAll() {
        return livroService.getAllLivros();
    }
    
    @GetMapping("/{id}")
    public Livro getLivro(@PathVariable Long id) {
        return livroService.getLivro(id);
    }

    @PostMapping
    public LivroResponseDTO createLivro(@RequestBody @Valid LivroDTO livro) {
        Livro entity = livroService.convertDTOToEntity(livro);
        livroService.create(entity);
        LivroResponseDTO responseDTO = livroService.convertEntityToResponseDTO(entity);
        return responseDTO;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity getIdLivro(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping
    public Livro putLivro(@PathVariable @RequestBody @Valid Long id, LivroDTO livro) {
        return livroService.put(id, livro);
    }

}

