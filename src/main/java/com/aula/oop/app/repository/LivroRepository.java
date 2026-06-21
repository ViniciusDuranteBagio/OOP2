package com.aula.oop.app.repository;

import com.aula.oop.app.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// LivroRepository -> camada responsavel por se comunicar diretamente com o banco de dados.
//
// JpaRepository<Livro, Long> -> ao estender essa interface, o Spring Data JPA
// GERA AUTOMATICAMENTE (em tempo de execucao, sem precisarmos escrever nada)
// uma implementacao com metodos como:
//   save(livro)        -> insere ou atualiza
//   findById(id)        -> busca por id
//   findAll()           -> lista todos
//   deleteById(id)       -> remove por id
//   existsById(id)       -> verifica se existe
// Os generics <Livro, Long> dizem: "essa entidade e Livro, e o tipo do ID e Long"
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // findByCodigo -> metodo customizado.
    // O Spring Data JPA e capaz de "entender" o nome do metodo e gerar a
    // query SQL correspondente automaticamente, SEM precisarmos escrever SQL.
    // "findBy" + "Codigo" = "SELECT * FROM livro WHERE codigo = ?"
    //
    // Retorna Optional<Livro> porque pode ou nao existir um livro com esse codigo
    // (Optional evita o uso de null e nos forca a tratar os dois casos).
    Optional<Livro> findByCodigo(String codigo);

    // existsByCodigo -> tambem gerado automaticamente pelo Spring Data JPA.
    // Retorna apenas um boolean (true/false), mais eficiente do que buscar
    // o objeto inteiro quando so precisamos saber "existe ou nao existe".
    // Usaremos este metodo no Service para checar duplicidade de codigo.
    boolean existsByCodigo(String codigo);

}
