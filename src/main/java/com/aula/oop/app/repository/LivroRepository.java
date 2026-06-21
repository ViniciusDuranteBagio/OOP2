package com.aula.oop.app.repository;

import com.aula.oop.app.model.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {
    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getAllLivros() {
        return livros;
    }

}
