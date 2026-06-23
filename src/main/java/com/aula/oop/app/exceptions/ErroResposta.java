package com.aula.oop.app.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroResposta {

    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
    private String caminho;
    private List<String> detalhes;

    public ErroResposta(int status, String erro, String mensagem, String caminho) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public ErroResposta(int status, String erro, String mensagem, String caminho, List<String> detalhes) {
        this(status, erro, mensagem, caminho);
        this.detalhes = detalhes;
    }

}