# CardioRadar SUS - Monitoramento de Risco Cardiovascular Municipal

Microsserviço desenvolvido em **Java 21** e **Quarkus** responsável por consolidar indicadores demográficos, epidemiológicos e operacionais para mensurar os índices de risco cardiovascular e alertar cenários críticos de saúde em nível municipal.

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
A API estará disponível externamente na porta configurada: `http://localhost:8082`.

### Rodando via Docker (JVM Mode)
Para compilar e subir a aplicação dentro de um container isolado integrado à rede do ecossistema:
```bash
# 1. Construir a imagem Docker
docker build -t fiap/cardioradar-sus:latest .

# 2. Executar o container exposto na porta 8082
docker run -i --rm -p 8082:8080 fiap/cardioradar-sus:latest
```

---

## 🛣️ Endpoints da API

Abaixo estão descritos os contratos disponíveis para o fluxo analítico do CardioRadar SUS.

### 1. Cadastrar Município Monitorado
Registra uma nova cidade na base de dados para acompanhamento contínuo.
* **Método:** `POST`
* **Rota:** `/api/v1/municipios`
* **Headers:** `Content-Type: application/json`
* **Payload de Requisição:**
  ```json
  {
    "codigoIbge": "3106200",
    "nome": "Belo Horizonte",
    "uf": "MG"
  }
  ```
* *Aviso de Integração:* Atente-se que o CardioRadar requer obrigatoriamente o código IBGE completo com 7 dígitos.

### 2. Cadastrar Indicador Cardiovascular
Registra variáveis de saúde de uma determinada competência mensal para servir de base no cálculo de risco.
* **Método:** `POST`
* **Rota:** `/api/v1/indicadores-cardiovasculares`
* **Headers:** `Content-Type: application/json`
* **Payload de Requisição:**
  ```json
  {
    "municipioId": 1,
    "competencia": "2026-07",
    "populacaoEstimada": 2315560,
    "populacaoIdosa": 320000,
    "atendimentosHipertensao": 1500,
    "atendimentosDiabetes": 800,
    "internacoesCardiovasculares": 520,
    "obitosCardiovasculares": 90,
    "procedimentosCardiovasculares": 2200,
    "fonte": "MANUAL"
  }
  ```

### 3. Calcular Risco Municipal
Executa o motor de regras ponderado para gerar a nota de índice de pressão cardiovascular, agregando dados epidemiológicos com a situação logística local.
* **Método:** `POST`
* **Rota:** `/api/v1/riscos/calcular`
* **Headers:** `Content-Type: application/json`
* **Payload de Requisição:**
  ```json
  {
    "municipioId": 1,
    "competencia": "2026-07",
    "pressaoMedicamentoId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "medicamento": "Losartana Potassica 50 mg",
    "consumoMensalMedio": 70,
    "estoqueAtual": 30
  }
  ```
* **Retorno Esperado (200 OK):**
  ```json
  {
    "indicePressaoCardiovascular": 92,
    "nivel": "CRITICO",
    "tendencia": "ESTAVEL"
  }
  ```

### 4. Consultas de Resultados e Visões Analíticas
* `GET /api/v1/riscos/municipios/{municipioId}` — Consulta detalhada do risco calculado na competência ativa.
* `GET /api/v1/riscos/municipios/{municipioId}/tendencia` — Análise preditiva do comportamento do índice.
* `GET /api/v1/riscos/ranking` — Lista ordenada dos municípios com piores índices de risco de saúde.
* `GET /api/v1/riscos/municipios/{municipioId}/dashboard` — Dashboard consolidado de indicadores locais.
