package br.com.samuel.compass.sicredi.application.usercase;


import br.com.samuel.compass.sicredi.application.resource.pauta.request.PautaRequest;
import br.com.samuel.compass.sicredi.application.resource.pauta.response.PautaResponse;
import reactor.core.publisher.Mono;

public interface PautaUseCase {
    Mono<PautaResponse> execute(PautaRequest request);
}
