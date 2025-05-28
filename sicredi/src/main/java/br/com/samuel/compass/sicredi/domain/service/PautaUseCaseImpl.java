package br.com.samuel.compass.sicredi.domain.service;

import br.com.samuel.compass.sicredi.application.resource.pauta.request.PautaRequest;
import br.com.samuel.compass.sicredi.application.resource.pauta.response.PautaResponse;
import br.com.samuel.compass.sicredi.application.usercase.PautaUseCase;
import br.com.samuel.compass.sicredi.domain.entities.Pauta;
import br.com.samuel.compass.sicredi.infrastructure.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PautaUseCaseImpl implements PautaUseCase {
    private final PautaRepository pautaRepository;

    @Override
    public Mono<PautaResponse> execute(PautaRequest request) {
        return pautaRepository.save(Pauta.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .build())
                .map(saved -> {
                    log.info("Criando nova pauta: {}", saved);
                    return new PautaResponse(
                            saved.getId(),
                            saved.getTitulo(),
                            saved.getDescricao());
                });
    }
}
