package com.aula.oop.app.mapper;

import com.aula.oop.app.dto.LivroRequestDto;
import com.aula.oop.app.dto.LivroResponseDto;
import com.aula.oop.app.model.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    LivroResponseDto toDto(Livro livro);

    Livro toEntity(LivroRequestDto livroRequestDto);

}
