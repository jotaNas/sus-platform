# Integração API Dados Abertos - DEMAS (SUS)

Microsserviço desenvolvido em **Java 21** e **Quarkus** para consumir e expor de forma amigável os dados do Portal de Dados Abertos do Ministério da Saúde. O projeto realiza a conversão automática de siglas de Estados (UFs) para códigos numéricos do IBGE e padroniza as respostas de erro da API do governo.

---

## 🚀 Como Rodar o Projeto Localmente

### Pré-requisitos
* Java 21 instalado
* Maven instalado (ou use o `./mvnw` incluído no projeto)
* Docker instalado (opcional)

### Modo de Desenvolvimento (Live Coding)
Para iniciar a aplicação localmente com suporte a recarregamento automático de código, execute:
```bash
mvn quarkus:dev
```
A API estará disponível em: `http://localhost:8080`

### Rodando via Docker (JVM Mode)
Para compilar e subir a aplicação dentro de um container isolado:
```bash
# 1. Construir a imagem Docker
docker build -t fiap/integracao-api:latest .

# 2. Executar o container exposto na porta 8080
docker run -i --rm -p 8080:8080 fiap/integracao-api:latest
```

---

## 🛣️ Endpoints da API

O projeto está configurado para receber e responder dados utilizando o padrão **`snake_case`** globalmente nas propriedades do JSON de saída. No entanto, as rotas locais de entrada aceitam as propriedades em **`camelCase`**.

### 1. Consultar Estoque de Medicamentos (Hórus/BNAFAR)
Busca a posição e o lote de medicamentos distribuídos na rede pública com base em filtros. Os campos não utilizados podem ser omitidos ou passados como `null`.

* **Método:** `POST`
* **Rota:** `/estoque-medicamentos/consultar`
* **Headers:** `Content-Type: application/json`
* **Payload Completo de Requisição:**
```json
{
  "limit": 2,
  "offset": 0,
  "uf": "PR",
  "codigoMunicipio": "410000",
  "codigoCnes": "8003041",
  "anomesPosicaoEstoque": 202607,
  "dataPosicaoEstoque": "2026-07-17",
  "codigoCatmat": "BR0606841U0042",
  "siglaProgramaSaude": "TB",
  "tipoProduto": "S",
  "siglaSistemaOrigem": "SI_BNAFAR"
}
```

### 2. Consultar Estabelecimentos de Saúde (CNES)
Lista os hospitais, clínicas e postos de saúde cadastrados de acordo com os critérios informados.

* **Método:** `POST`
* **Rota:** `/estabelecimento/consultar`
* **Headers:** `Content-Type: application/json`
* **Payload Completo de Requisição:**
```json
{
  "limit": 5,
  "offset": 0,
  "uf": "SP",
  "codigoMunicipio": 355030,
  "codigoTipoUnidade": 22,
  "status": 1,
  "estabelecimentoPossuiCentroCirurgico": 0,
  "estabelecimentoPossuiCentroObstetrico": 0,
  "dataAtualizacao": "2025-09-03"
}
```

### 3. Buscar Estabelecimento por Código CNES Único
Recupera a ficha completa e detalhada de um estabelecimento específico do SUS diretamente pela URL.

* **Método:** `GET`
* **Rota:** `/estabelecimento/consultar/{codigo_cnes}`
* **Exemplo de Chamada:**
```text
GET http://localhost:8080/estabelecimento/consultar/9629866
```

### 4. Consultar IBGE/SIDRA (`POST` /ibge/pesquisa)
Busca indicadores (ex: AVC/Hipertensão) da PNS.
Payload de exemplo:
```json
{
  "agregador": 4277,
  "variavel": 11754,
  "ano": [2023],
  "nivelGeografico": "N3",
  "localidades": ["13"],
  "sexo": [6794],
  "grupoIDades": [6795],
  "domicilios": [65445, 1]
}
```

---

## 🛠️ Tratamento de Erros e Validações

A aplicação conta com um gerenciador global de exceções (`ExceptionMapper`). Se você enviar uma sigla de estado inválida ou se a API do governo falhar, você receberá um retorno padronizado com o status HTTP correspondente.

**Exemplo de erro ao enviar uma UF inexistente (`"uf": "XX"`):**
```json
{
  "mensagem": "Sigla de UF inválida: XX",
  "status": 400,
  "timestamp": "2026-07-19T19:40:12"
}
```
