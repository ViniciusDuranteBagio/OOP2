package com.aula.oop.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

// @RestControllerAdvice -> torna esta classe um "interceptador global" de exceções.
// Qualquer exceção lançada em QUALQUER Controller da aplicação passa primeiro
// por aqui (se houver um @ExceptionHandler compatível) antes de virar uma resposta HTTP.
//
// Isso atende diretamente o requisito: "Todos os erros da aplicação devem ser
// tratados e retornar mensagens amigáveis ao cliente" -> centralizamos essa
// lógica em UM lugar só, em vez de repetir try/catch em cada Controller.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // --- 1) Tratamento de ResourceNotFoundException ---
    // Disparada no Service quando buscamos/atualizamos/deletamos um livro
    // que não existe no banco.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value()) // 404
                .error("Recurso nao encontrado")
                .message(ex.getMessage()) // mensagem definida quando lançamos a exceção
                .details(null)
                .build();

        // ResponseEntity<> nos permite controlar exatamente o status HTTP
        // E o corpo (body) da resposta que sera enviado ao cliente
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // --- 2) Tratamento de BusinessException ---
    // Disparada no Service quando uma regra de negocio e violada
    // (ex: codigo de livro duplicado).
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value()) // 409 -> "conflito" com o estado atual dos dados
                .error("Violacao de regra de negocio")
                .message(ex.getMessage())
                .details(null)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // --- 3) Tratamento de erros de validacao (@Valid nos DTOs) ---
    // O Spring lanca MethodArgumentNotValidException AUTOMATICAMENTE quando um
    // objeto anotado com @Valid (no Controller) falha em alguma validacao
    // (@NotBlank, @NotNull, @Min, @DecimalMin etc, definidas no LivroRequestDTO).
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {

        // getBindingResult().getFieldErrors() -> retorna a LISTA de TODOS os
        // campos que falharam na validacao (pode ser mais de um ao mesmo tempo).
        // Para cada erro de campo, montamos uma string "campo: mensagem"
        List<String> detalhes = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value()) // 400 -> dados enviados pelo cliente estao incorretos
                .error("Erro de validacao")
                .message("Um ou mais campos estao invalidos. Verifique os detalhes.")
                .details(detalhes)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // --- 4) Tratamento "catch-all" para qualquer outra exceção não prevista ---
    // Funciona como uma rede de seguranca: se algo inesperado acontecer
    // (ex: NullPointerException, erro de banco, etc), o cliente NUNCA vai
    // ver uma stack trace feia do Java, e sim uma mensagem amigavel e um 500.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()) // 500
                .error("Erro interno do servidor")
                .message("Ocorreu um erro inesperado. Tente novamente mais tarde.")
                .details(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
