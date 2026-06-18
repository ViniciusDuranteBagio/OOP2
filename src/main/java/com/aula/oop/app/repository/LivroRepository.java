package com.aula.oop.app.repository;

import com.aula.oop.app.dto.LivroDTO;
import com.aula.oop.app.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}

