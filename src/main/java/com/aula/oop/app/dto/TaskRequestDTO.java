package com.aula.oop.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String title;

    @Size(max = 500, message = "A descrição não pode ter mais de 500 caracteres")
    private String description;

    // Repare: SEM campo 'id' - o cliente não pode definir!
    // Repare: SEM campo 'completed' - começa sempre como false
}