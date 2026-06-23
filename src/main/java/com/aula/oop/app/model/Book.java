package com.aula.oop.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String title;
    @Column (nullable = false)
    private String autor;
    @Column (unique = true, nullable = false)
    private String code;
    @Column (nullable = false)
    private Integer anoPublicacao;
    @Column (nullable = false)
    private Double price;

}
