package com.aula.oop.app.service;

import com.aula.oop.app.dto.LivroRequestDTO;
import com.aula.oop.app.dto.LivroResponseDTO;
import com.aula.oop.app.exceptions.BusinessException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroResponseDTO cadastrar(LivroRequestDTO dto) { //Cadastrar livro
        if (livroRepository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException(
                    "Ja existe um livro cadastrado com o codigo '" + dto.getCodigo() + "'"
            );
        }

        Livro livro = Livro.builder()
                .titulo(dto.getTitulo())
                .autor(dto.getAutor())
                .codigo(dto.getCodigo())
                .anoPublicacao(dto.getAnoPublicacao())
                .preco(dto.getPreco())
                .build();

        Livro livroSalvo = livroRepository.save(livro); //Salva no banco
        return toResponseDTO(livroSalvo); //Converte para DTO
    }

    public List<LivroResponseDTO> listarTodos() { //Listar todos os livros cadastrados
        return livroRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) { //Busca livro pelo ID
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livro com id " + id + " nao foi encontrado"
                ));
        return toResponseDTO(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) { //Atualiza livro pelo ID
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Livro com id " + id + " nao foi encontrado"
                ));

        boolean codigoMudou = !livroExistente.getCodigo().equals(dto.getCodigo()); //Verifica se o codigo mudou, se sim joga mensagem
        if (codigoMudou && livroRepository.existsByCodigo(dto.getCodigo())) {
            throw new BusinessException(
                    "Ja existe um livro cadastrado com o codigo '" + dto.getCodigo() + "'"
            );
        }

        livroExistente.setTitulo(dto.getTitulo());
        livroExistente.setAutor(dto.getAutor());
        livroExistente.setCodigo(dto.getCodigo());                   //Atualiza os dados
        livroExistente.setAnoPublicacao(dto.getAnoPublicacao());
        livroExistente.setPreco(dto.getPreco());

        Livro livroAtualizado = livroRepository.save(livroExistente); //Salva no banco
        return toResponseDTO(livroAtualizado);
    }

    public void remover(Long id) { //Remove por ID
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Livro com id " + id + " nao foi encontrado" //Se nao existir joga mensagem
            );
        }
        livroRepository.deleteById(id);
    }

    private LivroResponseDTO toResponseDTO(Livro livro) { //Faz a conversão de Livro para LivroResponseDTO
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .codigo(livro.getCodigo())
                .anoPublicacao(livro.getAnoPublicacao())
                .preco(livro.getPreco())
                .build();
    }

}