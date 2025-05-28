package br.com.samuel.compass.sicredi.domain.service;

import br.com.samuel.compass.sicredi.application.resource.sessao.request.SessaoRequest;
import br.com.samuel.compass.sicredi.application.resource.sessao.response.SessaoResponse;
import br.com.samuel.compass.sicredi.application.usercase.SessaoUseCase;
import br.com.samuel.compass.sicredi.domain.entities.Sessao;
import br.com.samuel.compass.sicredi.infrastructure.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessaoUseCaseImpl implements SessaoUseCase {
    private final SessaoRepository sessaoRepository;

    @Override
    public Mono<SessaoResponse> execute(SessaoRequest request) {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusMinutes(request.getDuracao() != null ? request.getDuracao() : 1);
        log.info("Abrindo sessão para pauta {}: início {}, fim {}", request.getPautaId(), inicio, fim);

        return sessaoRepository.save(Sessao.builder()
                        .pautaId(request.getPautaId())
                        .inicio(inicio)
                        .fim(fim)
                        .build())
                .map(saved ->
                        new SessaoResponse(
                                saved.getId(),
                                saved.getPautaId(),
                                saved.getInicio(),
                                saved.getFim()));
    }

}

