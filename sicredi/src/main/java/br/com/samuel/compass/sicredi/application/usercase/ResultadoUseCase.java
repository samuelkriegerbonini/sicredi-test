package br.com.samuel.compass.sicredi.application.usercase;

import br.com.samuel.compass.sicredi.application.resource.voto.response.VotoResponse;
import reactor.core.publisher.Mono;

public interface ResultadoUseCase {
    Mono<VotoResponse> execute(String pautaId);
}

