package com.aula.oop.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               //criar getters, setters, hash equals e equals
@NoArgsConstructor  //criar construtor que não precisa passar dados
@AllArgsConstructor //criar construtor que precisa passar dados

public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
}
