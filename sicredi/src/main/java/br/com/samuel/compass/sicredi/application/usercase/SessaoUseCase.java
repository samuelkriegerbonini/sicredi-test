package br.com.samuel.compass.sicredi.application.usercase;

import br.com.samuel.compass.sicredi.application.resource.sessao.request.SessaoRequest;
import br.com.samuel.compass.sicredi.application.resource.sessao.response.SessaoResponse;
import reactor.core.publisher.Mono;

public interface SessaoUseCase {
    Mono<SessaoResponse> execute(SessaoRequest request);
}
