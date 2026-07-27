# RemedSUS - Gestão de Estoques e Dispensações

Microsserviço desenvolvido em **Java 21** e **Quarkus** voltado para o gerenciamento de unidades de saúde, cadastro de medicamentos, controle de lotes, dispensações e monitoramento de alertas de desabastecimento na rede pública.

---

## 🚀 Como Rodar o Projeto Localmente

### Pré-requisitos
* Java 21 instalado
* Maven instalado (ou use o `./mvnw` incluído no projeto)
* Docker instalado (opcional)

### Modo de Desenvolvimento (Live Coding)
Para iniciar a aplicação localmente com suporte a recarregamento automático de código, execute:
```bash
./mvnw quarkus:dev
```
A API estará disponível externamente na porta configurada: `http://localhost:8081`.

### Rodando via Docker (JVM Mode)
Para compilar e subir a aplicação dentro de um container isolado integrado à rede do ecossistema:
```bash
# 1. Construir a imagem Docker
docker build -t fiap/remedsus:latest .

# 2. Executar o container exposto na porta 8081
docker run -i --rm -p 8081:8080 fiap/remedsus:latest
```

---

## 🛣️ Endpoints da API

Abaixo estão descritos os contratos disponíveis para o fluxo operacional do RemedSUS.

### 1. Cadastrar Unidade de Saúde
Registra um novo ponto de atendimento na rede pública de saúde.
* **Método:** `POST`
* **Rota:** `/api/v1/unidades-saude`
* **Headers:** `Content-Type: application/json`
* **Payload de Requisição:**
  ```json
  {
    "nome": "UBS Bairro Limoeiro",
    "tipo": "UBS",
    "municipio": "Belo Horizonte",
    "bairro": "Centro"
  }
  ```
* **Resposta Esperada (201 Created):** Retorna o objeto persistido contendo o ID gerado.

### 2. Cadastrar Medicamento
Adiciona um novo medicamento na listagem oficial do sistema.
* **Método:** `POST`
* **Rota:** `/api/v1/medicamentos`
* **Headers:** `Content-Type: application/json`
* **Payload de Requisição:**
  ```json
  {
    "nome": "Losartana Potassica",
    "principioAtivo": "Losartana Potassica",
    "apresentacao": "50 mg comprimido"
  }
  ```

### 3. Registrar Entrada de Lote em Estoque
Registra a entrada física de quantidades de um medicamento em uma unidade de saúde específica.
* **Método:** `POST`
* **Rota:** `/api/v1/estoques/entradas`
* **Headers:** `Content-Type: application/json`
* **Payload de Requisição:**
  ```json
  {
    "unidadeId": 1,
    "medicamentoId": 2,
    "numeroLote": "LOS-2026-001",
    "validade": "2027-12-31",
    "quantidade": 100
  }
  ```
* *Nota:* O sistema configura automaticamente o limite de quantidade mínima de segurança padrão igual a `20` unidades.

### 4. Registrar Dispensação (Consumo)
Deduz unidades de estoque após a entrega/saída do medicamento para o cidadão.
* **Método:** `POST`
* **Rota:** `/api/v1/estoques/dispensacoes`
* **Headers:** `Content-Type: application/json`
* **Payload de Requisição:**
  ```json
  {
    "unidadeId": 1,
    "medicamentoId": 2,
    "quantidade": 70
  }
  ```

### 5. Consultar Saldo Atual de Estoque
Busca a posição atual de saldo consolidado de um medicamento em uma unidade específica.
* **Método:** `GET`
* **Rota:** `/api/v1/estoques/{unidadeId}/{medicamentoId}`
* **Exemplo de Chamada:** `GET http://localhost:8081/api/v1/estoques/1/2`
* **Exemplo de Retorno:**
  ```json
  {
    "unidadeId": 1,
    "medicamentoId": 2,
    "saldoAtual": 30
  }
  ```

### 6. Consultas Adicionais e Monitoramento
O microsserviço também expõe rotas de auditoria, alertas de desabastecimento e painéis gerenciais:
* `GET /api/v1/estoques/{estoqueId}/movimentacoes` — Histórico completo do lote.
* `GET /api/v1/alertas` — Listagem global de alertas críticos em aberto.
* `GET /api/v1/alertas/estoques/{estoqueId}` — Alertas específicos de um lote.
* `GET /api/v1/dashboard` — Dados analíticos gerais de consumo.
