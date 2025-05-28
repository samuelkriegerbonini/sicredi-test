package br.com.samuel.compass.sicredi.application.usercase;

import br.com.samuel.compass.sicredi.application.resource.voto.request.VotoRequest;
import reactor.core.publisher.Mono;

public interface VotoUseCase {
    Mono<Void> execute(VotoRequest request);
}
