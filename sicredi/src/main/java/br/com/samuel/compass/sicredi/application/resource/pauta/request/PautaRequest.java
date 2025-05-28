package br.com.samuel.compass.sicredi.application.resource.pauta.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaRequest {
    private String titulo;
    private String descricao;
}
