package com.aula.oop.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaskDTO {

        @NotBlank
        private String title;
        private String descreption;
        private Boolean completed;



}
