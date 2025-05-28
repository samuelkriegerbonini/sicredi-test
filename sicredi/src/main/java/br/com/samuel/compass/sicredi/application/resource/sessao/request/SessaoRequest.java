package br.com.samuel.compass.sicredi.application.resource.sessao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoRequest {
    private UUID pautaId;
    private Long duracao;
}