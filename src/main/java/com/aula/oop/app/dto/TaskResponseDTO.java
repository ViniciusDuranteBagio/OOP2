package com.aula.oop.app.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskResponseDTO {

        @NotBlank(message = "O título é obrigatório")
        private String title;
        private String description;
        private Boolean completed;
}
