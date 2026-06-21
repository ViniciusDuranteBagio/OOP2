package com.aula.oop.app.service;

import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
 public List<Livro> getAllLivros() {
        return livroRepository.getAllLivros();
    }
}
