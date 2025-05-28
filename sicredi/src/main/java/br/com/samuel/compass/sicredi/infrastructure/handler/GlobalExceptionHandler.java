package br.com.samuel.compass.sicredi.infrastructure.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex,
                                                           ServerWebExchange exchange) {
        log.warn("Erro de validação: {}", ex.getMessage());
        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return Mono.just(Map.of(
                "error", "Bad Request",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<Map<String, Object>> handleRuntime(RuntimeException ex, ServerWebExchange exchange) {
        log.error("Erro interno: ", ex);
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return Mono.just(Map.of(
                "error", "Internal Server Error",
                "message", "Ocorreu um erro inesperado. Tente novamente."
        ));
    }


}