package br.com.samuel.compass.sicredi.infrastructure.repository;

import br.com.samuel.compass.sicredi.domain.entities.Pauta;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface PautaRepository extends R2dbcRepository<Pauta, UUID> {}
