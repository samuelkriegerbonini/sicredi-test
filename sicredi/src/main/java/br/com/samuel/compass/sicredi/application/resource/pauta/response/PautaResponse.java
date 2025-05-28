package br.com.samuel.compass.sicredi.application.resource.pauta.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaResponse {
    private UUID id;
    private String titulo;
    private String descricao;
}