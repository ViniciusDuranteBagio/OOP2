package com.aula.oop.app.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String title;
    private String autor;
    private String code;
    private Integer anoPublicacao;
    private Double price;


}
