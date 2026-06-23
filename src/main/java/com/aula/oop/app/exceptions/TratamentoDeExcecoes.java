package com.aula.oop.app.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

    @RestControllerAdvice
    public class TratamentoDeExcecoes {

        @ExceptionHandler(LivroNaoEncontrado.class)
        public ResponseEntity<Map<String, Object>> tratarLivroNaoEncontrado(LivroNaoEncontrado ex) {
            return criarResposta(HttpStatus.NOT_FOUND, ex.getMessage());
        }

        @ExceptionHandler(LivroDuplicado.class)
        public ResponseEntity<Map<String, Object>> tratarCodigoDuplicado(LivroDuplicado ex) {
            return criarResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<Map<String, Object>> handleMethodNotAllowed(
                HttpRequestMethodNotSupportedException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("erro", "Método não permitido: " + ex.getMethod());
            body.put("status", 405);
            body.put("timestamp", LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(body);
        }


        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> tratarValidacoes(MethodArgumentNotValidException ex) {
            Map<String, String> campos = new HashMap<>();

            ex.getBindingResult()
                    .getFieldErrors()
                    .forEach(erro -> campos.put(erro.getField(), erro.getDefaultMessage()));

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("timestamp", LocalDateTime.now());
            resposta.put("status", HttpStatus.BAD_REQUEST.value());
            resposta.put("erro", "Dados invalidos");
            resposta.put("campos", campos);

            return ResponseEntity.badRequest().body(resposta);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, Object>> tratarErroGenerico(Exception ex) {
            return criarResposta(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor.");
        }

        private ResponseEntity<Map<String, Object>> criarResposta(HttpStatus status, String mensagem) {
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("timestamp", LocalDateTime.now());
            resposta.put("status", status.value());
            resposta.put("erro", mensagem);

            return ResponseEntity.status(status).body(resposta);

        }
    }

