package br.com.fiap.dtos.estabelecimento;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EstabelecimentoDTO(
    Integer codigoCnes,
    String numeroCnpjEntidade,
    String nomeRazaoSocial,
    String nomeFantasia,
    String naturezaOrganizacaoEntidade,
    String tipoGestao,
    String descricaoNivelHierarquia,
    String descricaoEsferaAdministrativa,
    Integer codigoTipoUnidade,
    String codigoCepEstabelecimento,
    String enderecoEstabelecimento,
    String numeroEstabelecimento,
    String bairroEstabelecimento,
    String numeroTelefoneEstabelecimento,
    Double latitudeEstabelecimentoDecimoGrau,
    Double longitudeEstabelecimentoDecimoGrau,
    String enderecoEmailEstabelecimento,
    String numeroCnpj,
    String codigoIdentificadorTurnoAtendimento,
    String descricaoTurnoAtendimento,
    String estabelecimentoFazAtendimentoAmbulatorialSus,
    String codigoEstabelecimentoSaude,
    String codigoUf,
    Integer codigoMunicipio,
    String descricaoNaturezaJuridicaEstabelecimento,
    String codigoMotivoDesabilitacaoEstabelecimento,
    Integer estabelecimentoPossuiCentroCirurgico,
    Integer estabelecimentoPossuiCentroObstetrico,
    Integer estabelecimentoPossuiCentroNeonatal,
    Integer estabelecimentoPossuiAtendimentoHospitalar,
    Integer estabelecimentoPossuiServicoApoio,
    Integer estabelecimentoPossuiAtendimentoAmbulatorial,
    String codigoAtividadeEnsinoUnidade,
    String codigoNaturezaOrganizacaoUnidade,
    String codigoNivelHierarquiaUnidade,
    String codigoEsferaAdministrativaUnidade,
    LocalDate dataAtualizacao
) {}

