package com.aula.oop.app.exceptions;

import com.aula.oop.app.dto.ErroResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorGlobalException {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarLivroNaoEncontrado(LivroNaoEncontradoException ex) {
        return criarResposta(HttpStatus.NOT_FOUND, "Livro nao encontrado", List.of(ex.getMessage()));
    }

    @ExceptionHandler(CodigoLivroDuplicadoException.class)
    public ResponseEntity<ErroResponse> tratarCodigoDuplicado(CodigoLivroDuplicadoException ex) {
        return criarResposta(HttpStatus.BAD_REQUEST, "Codigo duplicado", List.of(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarValidacao(MethodArgumentNotValidException ex) {
        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        return criarResposta(HttpStatus.BAD_REQUEST, "Dados invalidos", mensagens);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroGeral(Exception ex) {
        return criarResposta(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno",
                List.of("Ocorreu um erro inesperado. Tente novamente mais tarde.")
        );
    }

    private ResponseEntity<ErroResponse> criarResposta(HttpStatus status, String erro, List<String> mensagens) {
        return ResponseEntity.status(status).body(new ErroResponse(status.value(), erro, mensagens));
    }
}
