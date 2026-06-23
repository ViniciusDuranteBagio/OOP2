package com.aula.oop.app.controller;

import com.aula.oop.app.dto.LivroRequestDto;
import com.aula.oop.app.dto.LivroResponseDto;
import com.aula.oop.app.mapper.LivroMapper;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<LivroResponseDto> salvar(@Valid @RequestBody LivroRequestDto livroRequestDto) {
        Livro livro = livroMapper.toEntity(livroRequestDto);
        Livro livroSalvo = livroService.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroMapper.toDto(livroSalvo));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> listarTodos() {
        List<LivroResponseDto> livros = livroService.buscaTodos().stream().map(livroMapper::toDto).toList();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<LivroResponseDto> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(livroMapper.toDto(livroService.buscaPorCodigo(codigo)));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<LivroResponseDto> atualizarPorCodigo(@Valid @RequestBody LivroRequestDto requestDto, @PathVariable String codigo) {
        Livro livro = livroMapper.toEntity(requestDto);
        return ResponseEntity.ok(livroMapper.toDto(livroService.atualizar(livro, codigo)));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleltarPorCodigo(@PathVariable String codigo) {
        livroService.deletaPorCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

}
