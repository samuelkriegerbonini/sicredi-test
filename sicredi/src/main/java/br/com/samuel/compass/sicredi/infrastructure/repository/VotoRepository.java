package br.com.samuel.compass.sicredi.infrastructure.repository;

import br.com.samuel.compass.sicredi.domain.entities.Voto;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface VotoRepository extends R2dbcRepository<Voto, UUID> {

    Mono<Boolean> existsByPautaIdAndAssociadoId(UUID pautaId, String associadoId);

    Mono<Long> countByPautaIdAndVoto(UUID pautaId, Boolean voto);
}