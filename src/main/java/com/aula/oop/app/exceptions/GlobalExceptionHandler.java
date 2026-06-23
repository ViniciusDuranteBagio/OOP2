package com.aula.oop.app.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ApiErro> tratarLivroNaoEncontrado(LivroNaoEncontradoException ex) {

        ApiErro erro = new ApiErro(
                HttpStatus.NOT_FOUND.value(),
                "Livro não encontrado",
                List.of(ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(CodigoLivroDuplicadoException.class)
    public ResponseEntity<ApiErro> tratarCodigoDuplicado(CodigoLivroDuplicadoException ex) {

        ApiErro erro = new ApiErro(
                HttpStatus.BAD_REQUEST.value(),
                "Código duplicado",
                List.of(ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErro> tratarErroValidacao(MethodArgumentNotValidException ex) {

        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        ApiErro erro = new ApiErro(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagens
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErro> tratarJsonInvalido(HttpMessageNotReadableException ex) {

        ApiErro erro = new ApiErro(
                HttpStatus.BAD_REQUEST.value(),
                "JSON inválido",
                List.of("Verifique se o corpo da requisição está em formato JSON válido")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErro> tratarErroBanco(DataIntegrityViolationException ex) {

        ApiErro erro = new ApiErro(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de integridade dos dados",
                List.of("Não foi possível salvar os dados. Verifique se já existe um registro com as mesmas informações")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErro> tratarErroGeral(Exception ex) {

        ex.printStackTrace();

        ApiErro erro = new ApiErro(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                List.of(ex.getClass().getName() + ": " + ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}