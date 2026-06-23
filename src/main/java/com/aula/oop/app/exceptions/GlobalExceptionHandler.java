package com.aula.oop.app.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarErroValidacao(MethodArgumentNotValidException ex,
                                                            HttpServletRequest request) {
        List<String> detalhes = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Dados invalidos",
                "Um ou mais campos estao invalidos. Verifique os detalhes.",
                request.getRequestURI(),
                detalhes
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta);
    }

    @ExceptionHandler(CodigoDuplicadoException.class)
    public ResponseEntity<ErroResposta> tratarCodigoDuplicado(CodigoDuplicadoException ex,
                                                              HttpServletRequest request) {
        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.CONFLICT.value(),
                "Codigo duplicado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroResposta);
    }

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarLivroNaoEncontrado(LivroNaoEncontradoException ex,
                                                                 HttpServletRequest request) {
        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.NOT_FOUND.value(),
                "Livro nao encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResposta> tratarTipoInvalido(MethodArgumentTypeMismatchException ex,
                                                           HttpServletRequest request) {
        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Parametro invalido",
                "O valor informado para '" + ex.getName() + "' e invalido.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> tratarCorpoInvalido(HttpMessageNotReadableException ex,
                                                            HttpServletRequest request) {
        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Requisicao invalida",
                "O corpo da requisicao esta mal formatado ou contem dados em formato invalido.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroGenerico(Exception ex, HttpServletRequest request) {
        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                "Ocorreu um erro inesperado ao processar a sua solicitacao. Tente novamente mais tarde.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResposta);
    }

}