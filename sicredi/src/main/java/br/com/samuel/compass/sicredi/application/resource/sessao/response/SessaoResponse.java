package br.com.samuel.compass.sicredi.application.resource.sessao.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoResponse {
    private UUID id;
    private UUID pautaId;
    private LocalDateTime inicio;
    private LocalDateTime fim;
}

