package com.aula.oop.app.controller;

import com.aula.oop.app.dto.BookDTO;
import com.aula.oop.app.dto.BookResponseDTO;
import com.aula.oop.app.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // PEGA TODOS OS LIVROS DO REPOSITORIO
    @GetMapping
    public List<BookResponseDTO> listAll() {
        return bookService.getAllBooks();
    }

    // PEGA UM LIVRO DIRETO PELO ID
    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // CRIA E SALVA O LIVRO NO REPOSITORIO
    @PostMapping
    public BookResponseDTO createBook(@RequestBody BookDTO livro) {
        return bookService.createBook(livro);
    }

    // ATUALIZA TUDO DE UM LIVRO ESPECIFICO
    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id, @RequestBody BookDTO livro) {
        return bookService.updateBook(id, livro);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);

    }

}