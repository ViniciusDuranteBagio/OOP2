package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.CodigoDuplicado;
import com.aula.oop.app.exceptions.LivroNaoEncontrado;
import com.aula.oop.app.model.LivroModel;
import com.aula.oop.app.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService  {

    private LivroRepository livroRepository;

    public LivroService (LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO addLivro(LivroRequestDTO dto) {

        boolean exists = livroRepository.existsByCodigo(dto.getCodigo());

        if (exists) {
            throw new CodigoDuplicado("Código já registrado " + dto.getCodigo());
        }

        LivroModel entity = dtoToEntity(dto);
        return entityToResponseDTO(livroRepository.save(entity));

    }

    public List<LivroResponseDTO> getAllLivros(){
        return livroRepository.findAll()
                .stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    public LivroResponseDTO atualizaLivro(Long id, LivroRequestDTO dto) {

        LivroModel livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontrado("Nenhum livro encontrado com esse"));

        boolean codigoExists = livroRepository.existsByCodigoAndIdNot(dto.getCodigo(), id);

        if (codigoExists) {
            throw new CodigoDuplicado("Código já usado por outro livro");
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoLancamento(dto.getAnoLancamento());
        livro.setPreco(dto.getPreco());

        return entityToResponseDTO(livroRepository.save(livro));
    }

    public void  deletaLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNaoEncontrado("Não foi encontrado livro com id: " + id );
        }

        livroRepository.deleteById(id);
    }

    public LivroResponseDTO getLivroById(Long id) {
        LivroModel livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontrado("Não existe registro para este livro"));

        return entityToResponseDTO(livro);

    }

    private LivroModel dtoToEntity(LivroRequestDTO dto) {
        LivroModel livro = new LivroModel();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setCodigo(dto.getCodigo());
        livro.setAnoLancamento(dto.getAnoLancamento());
        livro.setPreco(dto.getPreco());

        return livro;
    }

    private LivroResponseDTO entityToResponseDTO(LivroModel livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigo(),
                livro.getAnoLancamento(),
                livro.getPreco()
        );

    }
}
