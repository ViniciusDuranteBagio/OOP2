package com.aula.oop.app.service;

import com.aula.oop.app.exceptions.BusinessException;
import com.aula.oop.app.exceptions.ResourceNotFoundException;
import com.aula.oop.app.model.Livro;
import com.aula.oop.app.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro){

        if(livroRepository.existsByCodigo(livro.getCodigo())){
            throw new BusinessException("Já existe um livro cadastrado com esse código!");
        }
        if (livro.getPreco() == null || livro.getPreco().compareTo(BigDecimal.ZERO) == 0){
            throw new BusinessException("O preco do livro não pode ser menor ou igual a zero.");
        }


        return livroRepository.save(livro);
    }

    public Livro atualizar(Livro livro, String codigo){

        Livro livroAtualizado = livroRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com esse código"));

        if (livro.getPreco() == null || livro.getPreco().compareTo(BigDecimal.ZERO) == 0){
            throw new BusinessException("O preco do livro não pode ser menor ou igual a zero.");
        }

        BeanUtils.copyProperties(livro, livroAtualizado, "codigo", "id");
        return livroRepository.save(livroAtualizado);
    }

    public Livro buscaPorCodigo(String codigo){
        return livroRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com esse código"));
    }

    public List<Livro> buscaTodos(){
        return livroRepository.findAll();
    }

    @Transactional
    public void deletaPorCodigo(String codigo){
        if(!livroRepository.existsByCodigo(codigo)){
            throw new ResourceNotFoundException("Livro não encontrado com esse código");
        }
        livroRepository.deleteByCodigo(codigo);
    }
}
