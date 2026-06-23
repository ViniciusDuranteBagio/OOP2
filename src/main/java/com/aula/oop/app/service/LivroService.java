package com.aula.oop.app.service;


import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.repository.LIvroRepositorio;
import org.springframework.stereotype.Service;



@Service
public class LivroService {
    private final LIvroRepositorio repositorio;

    public LivroService(LIvroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    private LivroResponseDTO
}
