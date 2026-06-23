package com.aula.oop.app.dto;

import java.math.BigDecimal;

public record LivroResponseDto(

        Long id,

        String titulo,

        String autor,

        String codigo,

        Integer anoPublicacao,

        BigDecimal preco
) {
}
