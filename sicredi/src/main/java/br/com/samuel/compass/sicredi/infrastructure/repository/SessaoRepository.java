package br.com.samuel.compass.sicredi.infrastructure.repository;

import br.com.samuel.compass.sicredi.domain.entities.Sessao;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface SessaoRepository extends R2dbcRepository<Sessao, UUID> {}

