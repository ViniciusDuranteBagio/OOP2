package com.aula.oop.app.repository;

import com.aula.oop.app.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivrosRepository extends JpaRepository<Livros, Long> {

    boolean existsByCodigo(String codigo);
}