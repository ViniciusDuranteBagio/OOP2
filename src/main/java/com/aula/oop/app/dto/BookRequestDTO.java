package com.aula.oop.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    private String title;
    private String autor;
    private String code;
    private Integer anoPublicacao;
    private Double price;

}

