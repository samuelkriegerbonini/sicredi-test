package br.com.samuel.compass.sicredi.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;


@Component
public class SchemaInitializer {

    private final DatabaseClient client;

    public SchemaInitializer(DatabaseClient client) {
        this.client = client;
    }

    @PostConstruct
    public void init() {
        client.sql("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";").then()
                .then(client.sql("""
                CREATE TABLE IF NOT EXISTS pautas (
                    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                    titulo TEXT NOT NULL,
                    descricao TEXT
                );
            """).then())
                .then(client.sql("""
                CREATE TABLE IF NOT EXISTS sessoes (
                    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                    pauta_id UUID NOT NULL,
                    data_abertura TIMESTAMP NOT NULL,
                    data_fechamento TIMESTAMP,
                    FOREIGN KEY (pauta_id) REFERENCES pautas(id) ON DELETE CASCADE
                );
            """).then())
                .then(client.sql("""
                CREATE TABLE IF NOT EXISTS votos (
                    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                    pauta_id UUID NOT NULL,
                    associado_cpf VARCHAR(11) NOT NULL,
                    voto BOOLEAN NOT NULL,
                    data_hora TIMESTAMP NOT NULL,
                    FOREIGN KEY (pauta_id) REFERENCES pautas(id) ON DELETE CASCADE
                );
            """).then())
                .subscribe();
    }
}