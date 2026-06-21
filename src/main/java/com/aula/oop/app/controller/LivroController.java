package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController -> combina @Controller + @ResponseBody.
// Isso significa: esta classe trata requisicoes HTTP, e cada metodo que
// retorna um objeto vai automaticamente serializar esse objeto para JSON
// na resposta (gracas ao Jackson, que vem junto com o spring-boot-starter-web).
@RestController

// @RequestMapping("/livros") -> define o prefixo de URL para TODOS os
// endpoints desta classe. Ou seja, todo metodo aqui comeca com "/livros".
@RequestMapping("/livros")

@RequiredArgsConstructor // Lombok gera o construtor que injeta o LivroService
public class LivroController {

    private final LivroService livroService;

    // ----------------------------------------------------------------
    // POST /livros -> Cadastrar um novo livro
    // ----------------------------------------------------------------
    @PostMapping
    public ResponseEntity<LivroResponseDTO> cadastrar(
            // @Valid -> ANTES de o metodo ser executado, o Spring valida o
            // objeto "dto" contra todas as anotacoes definidas em LivroRequestDTO
            // (@NotBlank, @NotNull, @Min, @DecimalMin...). Se algo falhar,
            // uma MethodArgumentNotValidException e lancada automaticamente
            // e capturada pelo GlobalExceptionHandler -> nosso metodo nem chega a rodar.
            @Valid @RequestBody LivroRequestDTO dto
    ) {
        LivroResponseDTO livroCriado = livroService.cadastrar(dto);

        // HttpStatus.CREATED = 201 -> codigo HTTP correto para "recurso criado com sucesso"
        // (mais semanticamente correto que devolver 200 OK em um POST de criacao)
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
    }

    // ----------------------------------------------------------------
    // GET /livros -> Listar todos os livros
    // ----------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listarTodos() {
        List<LivroResponseDTO> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros); // 200 OK
    }

    // ----------------------------------------------------------------
    // GET /livros/{id} -> Buscar um livro pelo ID
    // ----------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(
            // @PathVariable extrai o valor "{id}" da URL e injeta no parametro
            @PathVariable Long id
    ) {
        LivroResponseDTO livro = livroService.buscarPorId(id);
        // Se o livro nao existir, o Service ja lanca ResourceNotFoundException
        // antes mesmo de chegarmos aqui -> o GlobalExceptionHandler cuida do resto
        return ResponseEntity.ok(livro);
    }

    // ----------------------------------------------------------------
    // PUT /livros/{id} -> Atualizar os dados de um livro
    // ----------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LivroRequestDTO dto
    ) {
        LivroResponseDTO livroAtualizado = livroService.atualizar(id, dto);
        return ResponseEntity.ok(livroAtualizado);
    }

    // ----------------------------------------------------------------
    // DELETE /livros/{id} -> Remover um livro
    // ----------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        livroService.remover(id);

        // HttpStatus.NO_CONTENT = 204 -> codigo HTTP correto quando a operacao
        // deu certo mas nao ha conteudo para devolver no corpo da resposta
        return ResponseEntity.noContent().build();
    }

}
