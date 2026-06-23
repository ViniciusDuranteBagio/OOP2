package com.aula.oop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aula.oop.app.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    boolean existsByCodigo(String codigo);

}