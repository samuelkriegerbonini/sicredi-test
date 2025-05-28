package br.com.samuel.compass.sicredi.domain.service;

import br.com.samuel.compass.sicredi.application.resource.voto.request.VotoRequest;
import br.com.samuel.compass.sicredi.application.usercase.VotoUseCase;
import br.com.samuel.compass.sicredi.domain.entities.Voto;
import br.com.samuel.compass.sicredi.infrastructure.client.CpfValidatorProvider;
import br.com.samuel.compass.sicredi.infrastructure.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotoUseCaseImpl implements VotoUseCase {

    private final VotoRepository votoRepository;
    private final CpfValidatorProvider provider;

    @Override
    public Mono<Void> execute(VotoRequest request) {
        log.info("Validando CPF do associado {}", request.getAssociadoId());

        return provider.validateCpf(request.getAssociadoId())
                .flatMap(response -> {
                    if ("UNABLE_TO_VOTE".equalsIgnoreCase(response.getStatus())) {
                        return Mono.error(new RuntimeException("Associado não pode votar"));
                    }
                    return votoRepository.existsByPautaIdAndAssociadoId(request.getPautaId(), request.getAssociadoId());
                })
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Associado já votou nessa pauta"));
                    }
                    log.info("Registrando voto do associado {} na pauta {}: {}", request.getAssociadoId(), request.getPautaId(), request.getVoto());
                    return votoRepository.save(Voto.builder()
                            .pautaId(request.getPautaId())
                            .associadoId(request.getAssociadoId())
                            .voto(request.getVoto())
                            .dataHora(LocalDateTime.now())
                            .build()).then();
                });
    }
}