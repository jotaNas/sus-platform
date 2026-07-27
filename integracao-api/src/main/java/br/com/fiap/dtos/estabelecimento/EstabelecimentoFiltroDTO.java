package br.com.fiap.dtos.estabelecimento;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoFiltroDTO {

    @JsonProperty("codigoTipoUnidade")
    private Integer codigoTipoUnidade;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("codigoMunicipio")
    private Integer codigoMunicipio;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("estabelecimentoPossuiCentroCirurgico")
    private Integer estabelecimentoPossuiCentroCirurgico;

    @JsonProperty("estabelecimentoPossuiCentroObstetrico")
    private Integer estabelecimentoPossuiCentroObstetrico;

    @JsonProperty("dataAtualizacao")
    private String dataAtualizacao;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("offset")
    private Integer offset;
}
