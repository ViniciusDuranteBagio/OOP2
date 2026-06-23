package com.aula.oop.app.service;

import com.aula.oop.app.dto.BookDTO;
import com.aula.oop.app.dto.BookResponseDTO;
import com.aula.oop.app.exceptions.DuplicateResourceException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.exceptions.ValidationException;
import com.aula.oop.app.model.Book;
import com.aula.oop.app.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private void validateBook(BookDTO livro) {
        // VALIDACAO TITULO
        if (livro.getTitle() == null || livro.getTitle().trim().isEmpty()) {
            throw new ValidationException("O campo do titulo do livro e obrigatorio");
        }

        // VALIDACAO AUTOR
        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new ValidationException("O campo do autor e obrigatorio");
        }

        // VALIDACAO ANO DE PUBLICACAO
        if (livro.getAnoPublicacao() != null) {
            if (livro.getAnoPublicacao() > Year.now().getValue() || livro.getAnoPublicacao() < 1450) {
                throw new ValidationException
                        ("O ano de publicacao nao pode ser maior que o ano atual ou menor que o ano de 1450");
            }

        } else {throw new ValidationException("O campo ano de publcacao e obrigatorio");}

        // VALIDACAO PRECO
        if (livro.getPrice() != null) {
            if (livro.getPrice() <= 0) {
                throw new ValidationException("O preco do livro deve ser maior que zero");
            }

        } else {throw new ValidationException("O campo preco e obrigatorio");}
    }

    private void validateCodeToCreate(BookDTO livro) {
        // VALIDACAO CODIGO DO LIVRO
        if (livro.getCode() != null && !livro.getCode().trim().isEmpty()) {
            if (bookRepository.existsByCode(livro.getCode())) {
                throw new DuplicateResourceException("O codigo ja existe no banco: " + livro.getCode());
            }

        } else {throw new ValidationException("O campo codigo e obrigatorio");}
    }

    private void validateCodeToUpdate(Book book, BookDTO livro) {
        if (livro.getCode() != null && !livro.getCode().trim().isEmpty()) {
            if (!book.getCode().equals(livro.getCode()) &&
                    bookRepository.existsByCode(livro.getCode())) {
                throw new DuplicateResourceException("O codigo ja existe no banco: " + livro.getCode());
            }
        } else {throw new ValidationException("O campo codigo e obrigatorio");}
    }

    private void normalizeCode(BookDTO livro) {
        livro.setCode(livro.getCode().trim().toUpperCase());
    }

    public BookResponseDTO createBook(BookDTO livro) {
        validateBook(livro);
        validateCodeToCreate(livro);
        normalizeCode(livro);

        Book entity = dtoToEntity(livro);

        return entityToResponseDTO(bookRepository.save(entity));
    }

    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    // PEGA O LIVRO PELO ID
    public BookResponseDTO getBookById(Long id) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro nao encontrado para o ID informado"));

        return entityToResponseDTO(entity);
    }

    public BookResponseDTO updateBook(Long id, BookDTO livro) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro nao encontrado para o ID informado"));

        validateBook(livro);
        validateCodeToUpdate(book, livro);
        normalizeCode(livro);

        book.setTitle(livro.getTitle());
        book.setAutor(livro.getAutor());
        book.setCode(livro.getCode());
        book.setAnoPublicacao(livro.getAnoPublicacao());
        book.setPrice(livro.getPrice());

        return entityToResponseDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);

        } else {throw new ResourceNotFoundException("Livro nao encontrado para o ID informado");}
    }

    private Book dtoToEntity(BookDTO dto) {
        Book entity = new Book();

        entity.setTitle(dto.getTitle());
        entity.setAutor(dto.getAutor());
        entity.setCode(dto.getCode());
        entity.setAnoPublicacao(dto.getAnoPublicacao());
        entity.setPrice(dto.getPrice());

        return entity;
    }

    private BookResponseDTO entityToResponseDTO(Book entity) {
        BookResponseDTO dto = new BookResponseDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAutor(entity.getAutor());
        dto.setCode(entity.getCode());
        dto.setAnoPublicacao(entity.getAnoPublicacao());
        dto.setPrice(entity.getPrice());

        return dto;
    }

}
