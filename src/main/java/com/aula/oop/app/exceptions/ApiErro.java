package com.aula.oop.app.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErro {

    private LocalDateTime dataHora;
    private int status;
    private String erro;
    private List<String> mensagens;

    public ApiErro(int status, String erro, List<String> mensagens) {
        this.dataHora = LocalDateTime.now();
        this.status = status;
        this.erro = erro;
        this.mensagens = mensagens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}