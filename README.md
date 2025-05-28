# Sistema de Votação - Sicredi (WebFlux + R2DBC)

Este projeto é uma API reativa de votação construída com Java 21, Spring WebFlux e R2DBC. Permite a criação de pautas, abertura de sessões de votação, registro de votos e obtenção de resultados.

## 📦 Tecnologias

- Java 21
- Spring Boot 3 (WebFlux)
- Spring Data R2DBC
- PostgreSQL
- Docker (para banco de dados)
- Swagger OpenAPI
- WireMock (para mock de serviço de CPF)
- Lombok

---

## 🚀 Como Rodar

### 1. Clonar o Projeto

### 2. Subir Docker Compose
  Dentro da pasta sicredi via terminal rodar: 
```bash
docker compose up 
```

### 3. Rodar a aplicao do CPF MOCK

### 4. Rodar a aplicao do SICREDI

---

## 📬 Endpoints da API

| Método | Endpoint                              | Descrição                          |
|--------|----------------------------------------|------------------------------------|
| POST   | `/api/v1/pautas`                      | Cria uma nova pauta                |
| POST   | `/api/v1/pautas/{pautaId}/sessao`     | Abre sessão de votação             |
| POST   | `/api/v1/pautas/{pautaId}/voto`       | Registra voto de associado         |
| GET    | `/api/v1/pautas/{pautaId}/resultado`  | Consulta resultado da votação      |

---

## 💡 Exemplos com `curl`

### Criar Pauta

```bash
curl -X POST http://localhost:8080/api/v1/pautas \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Pauta sobre orçamento 2025",
    "descricao": "Discussão do orçamento anual"
}'
```

### Abrir Sessão

```bash
curl -X POST http://localhost:8080/api/v1/pautas/{pautaId}/sessao \
  -H "Content-Type: application/json" \
  -d '{
    "duracao": 10
}'
```

### Registrar Voto

```bash
curl -X POST http://localhost:8080/api/v1/pautas/{pautaId}/voto \
  -H "Content-Type: application/json" \
  -d '{
    "associadoId": "12345678901",
    "voto": true
}'
```

### Consultar Resultado

```bash
curl -X GET http://localhost:8080/api/v1/pautas/{pautaId}/resultado
```

---

## 📚 Swagger (OpenAPI)

Disponível em:

```
http://localhost:8080/swagger-ui.html
```

---

## ⚠️ Observações

- A criação de tabelas e extensões é feita automaticamente ao subir a aplicação.
- A validação do CPF está mockada via API CPF Mock.
- Não há verificação de voto duplicado neste MVP.
- Não há testes unitarios até o momento.

---

## 🧑‍💻 Autor

Samuel Bonini – Compass UOL  
[LinkedIn](https://www.linkedin.com/in/samuelbonini)

---

## 📄 Licença

Projeto livre para fins acadêmicos e de demonstração.
