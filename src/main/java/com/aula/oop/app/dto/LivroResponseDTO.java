package com.aula.oop.app.dto;

import java.math.BigDecimal;

public record LivroResponseDTO(

        Long id,
        String titulo,
        String autor,
        String codigo,
        Integer anoPublicacao,
        BigDecimal preco

) {}