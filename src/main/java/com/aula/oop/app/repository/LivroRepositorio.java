package com.aula.oop.app.repository;

import com.aula.oop.app.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {

    Optional<Livro> findByCodigo(String codigo);
}