package com.aula.oop.app.repository;

import com.aula.oop.app.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    //Cria uma consulta no banco

    Optional<Livro> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

}