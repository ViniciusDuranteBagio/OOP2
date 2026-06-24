package com.aula.oop.trabalhofinal.repository;

import com.aula.oop.trabalhofinal.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);
}