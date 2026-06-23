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

    private LivroService livroService;


    public LivroController(LivroService livroService){
        this.livroService=livroService;
    }

    @GetMapping
    public List<LivroResponseDTO> listarTodos() {

        return livroService.buscarTodosLivros();

    }



    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {

        return livroService.buscarPorId(id);

    }



    @PostMapping
    public LivroResponseDTO criarLivro(@RequestBody @Valid LivroRequestDTO livro) {

        return livroService.salvar(livro);

    }



    @PutMapping("/{id}")
    public LivroResponseDTO atualizarLivro(@PathVariable Long id,@RequestBody @Valid LivroRequestDTO livro) {

        return livroService.AtualizarLivro(livro, id);

    }



    @DeleteMapping("/deletar/{id}")
    public void deletarLivro(@PathVariable Long id) {

        livroService.deletarLivro(id);

    }




}
