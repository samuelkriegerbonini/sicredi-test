package br.com.samuel.compass.sicredi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("pautas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pauta {

    @Id
    private UUID id;

    @Column("titulo")
    private String titulo;

    @Column("descricao")
    private String descricao;
}