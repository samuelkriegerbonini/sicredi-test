package br.com.samuel.compass.sicredi.domain.service;

import br.com.samuel.compass.sicredi.application.resource.voto.response.VotoResponse;
import br.com.samuel.compass.sicredi.application.usercase.ResultadoUseCase;
import br.com.samuel.compass.sicredi.infrastructure.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResultadoUseCaseImpl implements ResultadoUseCase {

    private final VotoRepository votoRepository;

    @Override
    public Mono<VotoResponse> execute(String pautaId) {
        UUID id = UUID.fromString(pautaId);
        Mono<Long> totalSim = votoRepository.countByPautaIdAndVoto(id, true);
        Mono<Long> totalNao = votoRepository.countByPautaIdAndVoto(id, false);

        return Mono.zip(totalSim, totalNao)
                .map(tuple -> {
                    String resultado = tuple.getT1() > tuple.getT2() ? "Aprovada" : "Rejeitada";
                    log.info("Resultado da pauta {}: Sim={}, Não={}, Resultado={}", pautaId, tuple.getT1(), tuple.getT2(), resultado);
                    return new VotoResponse(id, tuple.getT1(), tuple.getT2(), resultado);
                });
    }
}
