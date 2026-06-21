package com.aula.oop.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// Livro -> esta e a ENTIDADE, ou seja, a classe que representa a tabela "livro"
// dentro do banco de dados. Ela e usada SOMENTE internamente (Service/Repository).
// Ela NUNCA deve ser exposta diretamente na API (por isso usamos os DTOs depois).

@Entity // diz ao JPA/Hibernate: "essa classe vira uma tabela no banco"
@Table(name = "livro") // nome explicito da tabela (boa pratica, evita ambiguidade)
@Getter // Lombok gera todos os getters (getId(), getTitulo(), etc.)
@Setter // Lombok gera todos os setters (setId(), setTitulo(), etc.)
@NoArgsConstructor // Lombok gera um construtor vazio Livro() -> o JPA EXIGE isso
@AllArgsConstructor // Lombok gera um construtor com todos os campos
@Builder // Lombok gera um "Livro.builder()...build()", forma alternativa e legivel de criar objetos
public class Livro {

    @Id // marca este campo como chave primaria (PK) da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // IDENTITY -> o proprio banco (H2) gera o valor do ID automaticamente
    // (auto incremento), atendendo o requisito "id gerado automaticamente"
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    // unique = true -> cria uma restricao (constraint) no banco garantindo
    // que nao existam dois registros com o mesmo "codigo".
    // Isso e uma camada EXTRA de seguranca, alem da verificacao que faremos
    // manualmente no Service (verificar duplicidade antes de salvar).
    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false)
    private BigDecimal preco;
    // BigDecimal e usado (em vez de double/float) porque e o tipo correto
    // para representar valores monetarios com precisao exata, sem erros de arredondamento.

}
