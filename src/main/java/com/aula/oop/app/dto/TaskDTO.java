package com.aula.oop.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaskDTO {
    private Long id;
    @NotBlank (message = "O título é obrigatório")
    private String title;
    private String description;
    private Boolean completed;
}
