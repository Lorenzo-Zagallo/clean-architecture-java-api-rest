package com.lorenzozagallo.ca24.adapters.in.exception;

import com.lorenzozagallo.ca24.domain.exception.ChampionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/* GlobalExceptionHandler: Intercepta exceções e retorna respostas
padronizadas de erro */

@ControllerAdvice
// indica que essa classe captura exceções lançadas em qualquer parte da aplicação
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // logger para registrar erros no console

    // trata a exceção ChampionNotFoundException
    @ExceptionHandler(ChampionNotFoundException.class)
    public ResponseEntity<ApiError> handleChampionNotFoundException(
            ChampionNotFoundException handleError) {
        logger.warn("Erro de domínio: {}", handleError.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação nos campos.",
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.unprocessableEntity().body(apiError);
                // retorna um HTTP 422 (Unprocessable Entity) e o objeto `apiError`
    }

    // trata exceções de validação de entrada (ex: @Valid em DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(
            MethodArgumentNotValidException handleError) {
        List<String> errors = handleError.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError
                        .getDefaultMessage()).toList();

        logger.warn("Erro de validação: {}", errors);

        ApiError apiError = new ApiError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação nos campos.",
                LocalDateTime.now(),
                errors
        );

        return ResponseEntity.badRequest().body(apiError);
    }

    // trata exceções inesperadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpectedException(Exception handleError) {
        String message = "Ops! Ocorreu um erro inesperado.";
        // mensagem genérica para erros desconhecidos

        logger.error(message, handleError);
        // registra o erro no log

        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message,
                LocalDateTime.now(),
                List.of(handleError.getMessage())
        );

        return ResponseEntity.internalServerError().body(apiError);
    }

    // classe interna para estruturar os erros na resposta HTTP
    public record ApiError(
            int status,
            String message,
            LocalDateTime timestamp,
            List<String> details
    ) { }

    /* classe imutável que representa a resposta JSON em caso de erro
    public record ApiError(String message) { } */
}
