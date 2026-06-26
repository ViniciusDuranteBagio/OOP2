package com.aula.oop.app.repository;

import com.aula.oop.app.model.Livros;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livros, Long> {

    boolean existsByCodigo(String codigo);
}
