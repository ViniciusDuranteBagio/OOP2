package com.aula.oop.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.SessionAttributes;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {


    private Long id;
    private String title;
    private String description;
    private Boolean completed;


}
