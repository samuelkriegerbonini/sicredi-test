package br.com.samuel.compass.sicredi.application.resource.voto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoResponse {
    private UUID pautaId;
    private Long totalSim;
    private Long totalNao;
    private String resultado;
}
