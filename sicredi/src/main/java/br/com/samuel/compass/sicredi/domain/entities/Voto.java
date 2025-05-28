package br.com.samuel.compass.sicredi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("votos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voto {

    @Id
    private UUID id;

    @Column("pauta_id")
    private UUID pautaId;

    @Column("associado_cpf")
    private String associadoId;

    @Column("voto")
    private Boolean voto;

    @Column("data_hora")
    private LocalDateTime dataHora;
}