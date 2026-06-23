package com.aula.oop.app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LivroRequestDTO {

    //recebe dados do cliente

    private String titulo;
    @NotBlank(message = "Título é obrigatório")

    private String autor;
    @NotBlank(message = "Autor é obrigatório")

    private String codigo;
    @NotBlank(message = "Código é obrigatório")

    private Integer anoPublicacao;
    @NotNull( message = "Ano é obrigatório")



    @NotNull( message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;







}
