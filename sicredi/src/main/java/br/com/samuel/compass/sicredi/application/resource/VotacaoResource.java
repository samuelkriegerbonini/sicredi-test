package br.com.samuel.compass.sicredi.application.resource;

import br.com.samuel.compass.sicredi.application.resource.pauta.request.PautaRequest;
import br.com.samuel.compass.sicredi.application.resource.pauta.response.PautaResponse;
import br.com.samuel.compass.sicredi.application.resource.sessao.request.SessaoRequest;
import br.com.samuel.compass.sicredi.application.resource.sessao.response.SessaoResponse;
import br.com.samuel.compass.sicredi.application.resource.voto.request.VotoRequest;
import br.com.samuel.compass.sicredi.application.resource.voto.response.VotoResponse;
import br.com.samuel.compass.sicredi.application.usercase.PautaUseCase;
import br.com.samuel.compass.sicredi.application.usercase.ResultadoUseCase;
import br.com.samuel.compass.sicredi.application.usercase.SessaoUseCase;
import br.com.samuel.compass.sicredi.application.usercase.VotoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Votação", description = "Operações para gerenciamento de pautas e votações")
public class VotacaoResource {
    private final PautaUseCase criarPauta;
    private final SessaoUseCase abrirSessao;
    private final VotoUseCase registrarVoto;
    private final ResultadoUseCase obterResultado;

    @Operation(summary = "Criar nova pauta")
    @PostMapping("/pautas")
    public Mono<PautaResponse> criarPauta(@RequestBody PautaRequest request) {
        log.info("Recebida requisição para criar pauta: {}", request);
        return criarPauta.execute(request);
    }

    @Operation(summary = "Abrir sessão de votação")
    @PostMapping("/pautas/{pautaId}/sessao")
    public Mono<SessaoResponse> abrirSessao(@PathVariable UUID pautaId,
                                            @RequestBody(required = false) SessaoRequest body) {
        SessaoRequest request = new SessaoRequest(pautaId,
                body != null && body.getDuracao() != null ? body.getDuracao() : null);
        log.info("Recebida requisição para abrir sessão: {}", request);
        return abrirSessao.execute(request);
    }

    @Operation(summary = "Registrar voto em pauta")
    @PostMapping("/pautas/{pautaId}/voto")
    public Mono<Void> votar(@PathVariable UUID pautaId, @RequestBody VotoRequest request) {
        request.setPautaId(pautaId);
        log.info("Recebida requisição de voto: {}", request);
        return registrarVoto.execute(request);
    }

    @Operation(summary = "Obter resultado da votação")
    @GetMapping("/pautas/{pautaId}/resultado")
    public Mono<VotoResponse> resultado(@PathVariable String pautaId) {
        log.info("Recebida requisição de resultado da pauta {}", pautaId);
        return obterResultado.execute(pautaId);
    }
}
