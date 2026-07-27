package br.com.fiap.dtos.pesquisaibge;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PesquisaFiltroDTO {

    @JsonProperty("agregador")
    private Integer agregador;

    @JsonProperty("variavel")
    private Integer variavel;
    
    @JsonProperty("ano")
    private List<Integer> ano;

    @JsonProperty("nivelGeografico")
    private String nivelGeografico;

    @JsonProperty("localidades")
    private List<Integer> localidades;

    @JsonProperty("sexo")
    private List<Integer> sexo;

    @JsonProperty("grupoIdades")
    private List<Integer> grupoIdades;

    @JsonProperty("domicilios")
    private List<Integer> domicilios;

}
