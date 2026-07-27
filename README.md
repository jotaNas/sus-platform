# SUS Platform - Ecossistema Integrado de Saúde Digital

A **SUS Platform** é uma solução distribuída baseada em microsserviços para monitoramento logístico e epidemiológico da saúde pública brasileira. O ecossistema unifica dados oficiais de saúde, gestão de farmácia popular e análise preditiva de riscos cardiovasculares.

O projeto é composto por três microsserviços desenvolvidos em **Java 21** com **Quarkus**, suportados por instâncias isoladas de bancos de dados **PostgreSQL 16**.

---

## 🏗️ Arquitetura do Ecossistema

O ambiente é totalmente orquestrado via Docker e estruturado da seguinte forma:

| Serviço / Container | Endereço Interno (Rede Docker) | Porta Externa (Host) | Finalidade Principal |
| :--- | :--- | :--- | :--- |
| **`integracao-api`** | `http://integracao-api:8080` | `8080` | Consome dados brutos do CNES, BNAFAR (Hórus) e IBGE/SIDRA. |
| **`remedsus`** | `http://remedsus:8080` | `8081` | Gerencia unidades de saúde, estoque de lotes e dispensação de remédios. |
| **`cardioradar-sus`** | `http://cardioradar-sus:8080` | `8082` | Calcula o índice de risco cardiovascular de municípios monitorados. |
| **`remedsus-postgres`** | `remedsus-postgres:5432` | `5433` | Banco de dados relacional para persistência do RemedSUS. |
| **`cardioradar-postgres`**| `cardioradar-postgres:5432`| `5434` | Banco de dados relacional para persistência do CardioRadar. |

---

## 🚀 Como Inicializar o Ambiente Completo

### Pré-requisitos
* Docker e Docker Compose instalados.
* Postman instalado para execução dos testes.

### Passo 1: Clonar e Estruturar as Pastas
Certifique-se de que a estrutura de diretórios do seu projeto está alinhada com as diretivas de contexto do `docker-compose.yml`:
```text
sus-platform/
├── docker-compose.yml
├── CardioRadarSus.postman_collection.json
├── integracao-api/
├── remedsus/remedsus/
└── cardioradar-sus/
```

### Passo 2: Subir os Containers
Na raiz do projeto principal (`sus-platform`), execute o comando abaixo para compilar as imagens e inicializar todos os serviços de forma ordenada:
```bash
docker compose up -d --build
```
*O ecossistema utiliza travas de inicialização (`depends_on` com `service_healthy`). Os microsserviços só iniciarão após a validação de prontidão das bases PostgreSQL.*

---

## 🧪 Roteiro de Teste Ponta a Ponta (E2E) via Postman

Para simular o fluxo completo de integração e o comportamento do motor de regras, disponibilizamos o arquivo **`CardioRadarSus.postman_collection.json`** na raiz do projeto.

### Como Executar o Fluxo no Postman:
1. Abra o **Postman**.
2. Clique em **Import** e selecione o arquivo `CardioRadarSus.postman_collection.json`.
3. Certifique-se de que o seu ambiente está limpo (banco de dados sem dados duplicados).
4. Execute as 10 requisições contidas na coleção seguindo a ordem numérica de cima para baixo.

### Automação de Variáveis:
A coleção possui scripts de teste em JavaScript anexados para automatizar a captura de chaves. O Postman salvará de forma dinâmica nas suas **variáveis globais** (`globals`) as seguintes informações geradas em tempo de execução:
* `{{unidadeId}}` — Capturado após o cadastro da UBS no RemedSUS.
* `{{medicamentoId}}` — Capturado após o cadastro da Losartana no RemedSUS.
* `{{municipioId}}` — Capturado após o cadastro da cidade no CardioRadar.

---

## 📊 Cenário de Cálculo Esperado

Ao executar a rota **`Calcular o risco`**, o motor de regras ponderado processará os dados consolidados do lote e da epidemiologia do município gerando os seguintes resultados analíticos:

* **Dias de Cobertura de Estoque:** `12 dias` (Saldo de 30 unidades / Consumo médio de 70)
* **Score de Cobertura:** `100`
* **Índice de Pressão Cardiovascular Calculado:** `92`
* **Nível do Cenário:** `CRITICO`
* **Tendência Identificada:** `ESTAVEL`

---
