package br.com.fiap.dtos.estoquemedicamentos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EstoqueMedicamentosDTO(
    Integer codigoUf,
    Integer codigoMunicipio,
    Integer codigoCnes,
    LocalDate dataPosicaoEstoque,
    String codigoCatmat,
    Integer quantidadeEstoque,
    String numeroLote,
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssx")
    OffsetDateTime dataValidade,
    
    String tipoProduto,
    String siglaProgramaSaude,
    String descricaoProgramaSaude,
    String siglaSistemaOrigem,
    String descricaoProduto,
    String municipio,
    String uf,
    String razaoSocial,
    String nomeFantasia,
    String cep,
    String logradouro,
    String numeroEndereco,
    String bairro,
    String telefone,
    Double latitude,
    Double longitude,
    String email
) {}
