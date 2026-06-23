package com.aula.oop.app.controller;


import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biblioteca")
public class LivroController {

    LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/listar")
    public List<LivroResponseDTO> listAll(){
        return livroService.getAllLivros();
    }

    @GetMapping("/listar/{id}")
    public LivroResponseDTO listById(@PathVariable Long id){
        return livroService.getLivroById(id);
    }

    @PostMapping("/criacao")
    public ResponseEntity<LivroResponseDTO> addLivro(@RequestBody @Valid LivroRequestDTO livro){
        LivroResponseDTO resposta = livroService.addLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PutMapping("/atualizar/{id}")
    public LivroResponseDTO atualizaLivro(@PathVariable Long id, @RequestBody @Valid LivroRequestDTO livro){
        return livroService.atualizaLivro(id, livro);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long id){
        livroService.deletaLivro(id);
        return ResponseEntity.ok("Livro excluido com sucesso");

    }
}
