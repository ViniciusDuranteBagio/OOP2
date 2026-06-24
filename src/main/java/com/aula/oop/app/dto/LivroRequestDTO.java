package com.aula.oop.app.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequestDTO {

    //recebe dados do cliente
    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "Código é obrigatório")
    private String codigo;

    @NotNull( message = "Ano é obrigatório")
    private Integer anoPublicacao;


    @NotNull( message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;







}
