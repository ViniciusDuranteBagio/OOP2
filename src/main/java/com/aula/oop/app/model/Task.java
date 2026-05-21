package com.aula.oop.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private long id;
    private String title;
    private String description;
    private Boolean completed;

}
