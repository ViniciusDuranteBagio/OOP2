package com.aula.oop.app.repository;

import com.aula.oop.app.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Boolean existsByCodigo(String codigo);

    Boolean existsByCodigoAndIdNot(String codigo, long id);
}