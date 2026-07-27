package com.fiap.cardioradar.domain.service;

import com.fiap.cardioradar.domain.IndicadorCardiovascular;
import com.fiap.cardioradar.domain.PressaoMedicamento;
import com.fiap.cardioradar.domain.vo.NivelRisco;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fiap.cardioradar.domain.RiscoMunicipal;

import com.fiap.cardioradar.domain.vo.TendenciaRisco;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculadoraIndicePressaoCardiovascular {

    private static final double PESO_INTERNACOES = 0.40;
    private static final double PESO_ESTOQUE = 0.40;
    private static final double PESO_POPULACAO_IDOSA = 0.20;

    public RiscoMunicipal calcular(
            IndicadorCardiovascular indicador,
            PressaoMedicamento pressao,
            RiscoMunicipal riscoAnterior
    ) {
        validarParametros(indicador, pressao);
        validarMesmoMunicipio(indicador, pressao);

        int totalInternacoes =
                indicador.totalInternacoesCardiovasculares();

        int scoreInternacoes =
                calcularScoreInternacoes(totalInternacoes);

        int scoreEstoque =
                calcularScoreEstoque(
                        pressao.getDiasCobertura()
                );

        int scorePopulacaoIdosa =
                calcularScorePopulacaoIdosa(
                        indicador.percentualPopulacaoIdosa()
                );

        int indice = calcularIndice(
                scoreInternacoes,
                scoreEstoque,
                scorePopulacaoIdosa
        );

        NivelRisco nivel =
                classificar(indice);

        TendenciaRisco tendencia =
                calcularTendencia(
                        indice,
                        riscoAnterior
                );

        return new RiscoMunicipal(
                UUID.randomUUID(),
                indicador.getMunicipioId(),
                indicador.getId(),
                indicador.getCompetencia(),
                indice,
                nivel,
                tendencia,
                LocalDateTime.now()
        );
    }

    private void validarParametros(
            IndicadorCardiovascular indicador,
            PressaoMedicamento pressao
    ) {
        Objects.requireNonNull(
                indicador,
                "Indicador cardiovascular é obrigatório."
        );

        Objects.requireNonNull(
                pressao,
                "Pressão do medicamento é obrigatória."
        );
    }

    private void validarMesmoMunicipio(
            IndicadorCardiovascular indicador,
            PressaoMedicamento pressao
    ) {
        if (!Objects.equals(
                indicador.getMunicipioId(),
                pressao.getMunicipioId()
        )) {
            throw new IllegalArgumentException(
                    "Indicador cardiovascular e pressão do medicamento "
                            + "pertencem a municípios diferentes."
            );
        }
    }

    private int calcularIndice(
            int scoreInternacoes,
            int scoreEstoque,
            int scorePopulacaoIdosa
    ) {
        double resultado =
                scoreInternacoes * PESO_INTERNACOES
                        + scoreEstoque * PESO_ESTOQUE
                        + scorePopulacaoIdosa
                        * PESO_POPULACAO_IDOSA;

        return Math.max(
                0,
                Math.min(
                        (int) Math.round(resultado),
                        100
                )
        );
    }

    private int calcularScoreInternacoes(
            int totalInternacoes
    ) {
        if (totalInternacoes < 0) {
            throw new IllegalArgumentException(
                    "Total de internações não pode ser negativo."
            );
        }

        if (totalInternacoes >= 500) {
            return 100;
        }

        if (totalInternacoes >= 300) {
            return 80;
        }

        if (totalInternacoes >= 150) {
            return 60;
        }

        if (totalInternacoes >= 50) {
            return 35;
        }

        return 15;
    }

    private int calcularScoreEstoque(
            int diasCobertura
    ) {
        if (diasCobertura < 0) {
            throw new IllegalArgumentException(
                    "Dias de cobertura não pode ser negativo."
            );
        }

        if (diasCobertura <= 15) {
            return 100;
        }

        if (diasCobertura <= 30) {
            return 80;
        }

        if (diasCobertura <= 60) {
            return 60;
        }

        if (diasCobertura <= 90) {
            return 35;
        }

        return 15;
    }

    private int calcularScorePopulacaoIdosa(
            double percentualPopulacaoIdosa
    ) {
        if (percentualPopulacaoIdosa < 0) {
            throw new IllegalArgumentException(
                    "Percentual da população idosa não pode ser negativo."
            );
        }

        if (percentualPopulacaoIdosa > 100) {
            throw new IllegalArgumentException(
                    "Percentual da população idosa não pode ser maior que 100."
            );
        }

        if (percentualPopulacaoIdosa >= 20) {
            return 100;
        }

        if (percentualPopulacaoIdosa >= 15) {
            return 80;
        }

        if (percentualPopulacaoIdosa >= 10) {
            return 60;
        }

        if (percentualPopulacaoIdosa >= 5) {
            return 35;
        }

        return 15;
    }

    private NivelRisco classificar(
            int indice
    ) {
        if (indice >= 81) {
            return NivelRisco.CRITICO;
        }

        if (indice >= 61) {
            return NivelRisco.ALTO_RISCO;
        }

        if (indice >= 31) {
            return NivelRisco.ATENCAO;
        }

        return NivelRisco.ESTAVEL;
    }

    private TendenciaRisco calcularTendencia(
            double indiceAtual,
            RiscoMunicipal riscoAnterior
    ) {
        if (riscoAnterior == null) {
            return TendenciaRisco.ESTAVEL;
        }

        double indiceAnterior =
                riscoAnterior.getIndicePressaoCardiovascular();

        if (indiceAtual > indiceAnterior) {
            return TendenciaRisco.CRESCIMENTO;
        }

        if (indiceAtual < indiceAnterior) {
            return TendenciaRisco.REDUCAO;
        }

        return TendenciaRisco.ESTAVEL;
    }
}