package br.com.fiap.dtos.estoquemedicamentos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueMedicamentosFiltroDTO {

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("codigoMunicipio")
    private String codigoMunicipio;

    @JsonProperty("codigoCnes")
    private String codigoCnes;

    @JsonProperty("anomesPosicaoEstoque")
    private Integer anomesPosicaoEstoque;

    @JsonProperty("dataPosicaoEstoque")
    private String dataPosicaoEstoque;

    @JsonProperty("codigoCatmat")
    private String codigoCatmat;

    @JsonProperty("siglaProgramaSaude")
    private String siglaProgramaSaude;

    @JsonProperty("tipoProduto")
    private String tipoProduto;

    @JsonProperty("siglaSistemaOrigem")
    private String siglaSistemaOrigem;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("offset")
    private Integer offset;
}
