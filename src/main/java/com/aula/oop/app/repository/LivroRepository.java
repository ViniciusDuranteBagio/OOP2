package com.aula.oop.app.repository;


import com.aula.oop.app.livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<livro, Long> {

    // Esse método vai checar se o código já existe antes de cadastrar
    boolean existsByCodigo(String codigo);

    // Esse vai checar se o código existe, ignorando o ID do próprio livro (útil no PUT)
    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
