package br.com.samuel.compass.sicredi.application.resource.voto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequest {

    @JsonIgnore
    private UUID pautaId;
    private String associadoId;
    private Boolean voto;
}