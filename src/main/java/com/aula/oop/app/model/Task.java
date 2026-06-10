package com.aula.oop.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {



    private String Title;
    private String Descreption;
    private Boolean Completed;


}
