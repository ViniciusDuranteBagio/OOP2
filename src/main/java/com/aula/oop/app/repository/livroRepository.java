package com.aula.oop.app.repository;

import com.aula.oop.app.model.livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface livroRepository extends JpaRepository<livro, Long> {
    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);

}