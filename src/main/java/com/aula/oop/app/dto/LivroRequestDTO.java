package com.aula.oop.app.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO {


    @NotBlank(message = "O título é obrigatório")
     String titulo;

    @NotBlank(message = "O autor é obrigatório")
   String autor;

    @NotBlank(message = "O codigo é obrigatório")
 String codigo;

    @NotNull(message = "O Ano é obrigatório" )
   int anoPublicacao;

    @NotNull(message="Preço obrigatório")
   double preco;

}
