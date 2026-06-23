package com.aula.oop.app.repository;

import com.aula.oop.app.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LIvroRepositorio extends JpaRepository<Livro,Long> {



}
