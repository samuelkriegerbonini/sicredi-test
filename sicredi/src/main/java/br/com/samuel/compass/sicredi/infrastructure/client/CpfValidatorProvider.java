package br.com.samuel.compass.sicredi.infrastructure.client;

import br.com.samuel.compass.sicredi.infrastructure.client.response.CpfStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CpfValidatorProvider {

    private final WebClient.Builder webClientBuilder;

    public Mono<CpfStatusResponse> validateCpf(String cpf) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/users/{cpf}", cpf)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new IllegalArgumentException("CPF inválido")))
                .bodyToMono(String.class)
                .map(response -> {
                    if (response.contains("ABLE_TO_VOTE")) {
                        return new CpfStatusResponse("ABLE_TO_VOTE");
                    } else if (response.contains("UNABLE_TO_VOTE")) {
                        return new CpfStatusResponse("UNABLE_TO_VOTE");
                    } else {
                        throw new RuntimeException("Resposta inesperada da API: " + response);
                    }
                });
    }
}
