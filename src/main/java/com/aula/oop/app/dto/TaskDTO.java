package com.aula.oop.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getters, setters, equals e hash code
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    @NotBlank(message = "O título é obrigatório")
    private String title;
    private String description;
    private Boolean completed;


}
