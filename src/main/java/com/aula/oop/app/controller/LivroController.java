package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/livros")
public class LivroController {
    LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    //listar todos os livros
    @GetMapping
    public List<LivroResponseDTO> listarLivros() {
        return livroService.listarTodos();
    }

    //buscar livro pelo id

    @GetMapping("/{id}")
    public LivroResponseDTO buscarId (@PathVariable Long id){
        return livroService.buscarPorId(id);
    }


    //cadastrar um novo livro

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar (@RequestBody @Valid LivroRequestDTO livro){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(livroService.cadastrarLivro(livro));
        // aqui coloquei o CREATED pois vi que ele retorna o 201, o que faz mais sentido já que se trata de uma criação de item
    }

    //deletar livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.ok().build();
    }

    //atualizar algum livro
    @PutMapping("/{id}")
    public LivroResponseDTO atualizar (@PathVariable Long id, @RequestBody @Valid LivroRequestDTO livro){
        return livroService.atualizarLivro(id, livro);
    }

}
