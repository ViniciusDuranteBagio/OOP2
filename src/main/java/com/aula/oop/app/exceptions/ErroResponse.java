package com.aula.oop.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErroResponse {

    private int status;
    private String mensagem;
}
