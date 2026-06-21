package com.aula.oop.app.controller;

import com.aula.oop.app.model.Livro;
import com.aula.oop.app.service.LivroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/livros")
public class LivroController {

    public LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listAll() {
        return livroService.getAllLivros();
    }
}
