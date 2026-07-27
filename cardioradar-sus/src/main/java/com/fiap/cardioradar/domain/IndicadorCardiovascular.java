package com.fiap.cardioradar.domain;

import com.fiap.cardioradar.domain.vo.FonteDado;

import java.time.LocalDateTime;
import java.time.YearMonth;

import java.util.UUID;

public class IndicadorCardiovascular {

    private final UUID id;
    private final UUID municipioId;
    private final YearMonth competencia;

    private final int populacaoEstimada;
    private final int populacaoIdosa;
    private final int atendimentosHipertensao;
    private final int atendimentosDiabetes;
    private final int internacoesCardiovasculares;
    private final int obitosCardiovasculares;
    private final int procedimentosCardiovasculares;

    private final FonteDado fonte;
    private final LocalDateTime registradoEm;

    public IndicadorCardiovascular(
            UUID id,
            UUID municipioId,
            YearMonth competencia,
            int populacaoEstimada,
            int populacaoIdosa,
            int atendimentosHipertensao,
            int atendimentosDiabetes,
            int internacoesCardiovasculares,
            int obitosCardiovasculares,
            int procedimentosCardiovasculares,
            FonteDado fonte,
            LocalDateTime registradoEm
    ) {
        validarId(id, "Id do indicador é obrigatório.");
        validarId(municipioId, "Município é obrigatório.");

        if (competencia == null) {
            throw new IllegalArgumentException(
                    "Competência é obrigatória."
            );
        }

        if (populacaoEstimada <= 0) {
            throw new IllegalArgumentException(
                    "População estimada deve ser maior que zero."
            );
        }

        if (populacaoIdosa < 0) {
            throw new IllegalArgumentException(
                    "População idosa não pode ser negativa."
            );
        }

        if (populacaoIdosa > populacaoEstimada) {
            throw new IllegalArgumentException(
                    "População idosa não pode ser maior que a população estimada."
            );
        }

        if (fonte == null) {
            throw new IllegalArgumentException(
                    "Fonte do dado é obrigatória."
            );
        }

        this.id = id;
        this.municipioId = municipioId;
        this.competencia = competencia;
        this.populacaoEstimada = populacaoEstimada;
        this.populacaoIdosa = populacaoIdosa;

        this.atendimentosHipertensao = validarValorNaoNegativo(
                atendimentosHipertensao,
                "Atendimentos de hipertensão"
        );

        this.atendimentosDiabetes = validarValorNaoNegativo(
                atendimentosDiabetes,
                "Atendimentos de diabetes"
        );

        this.internacoesCardiovasculares = validarValorNaoNegativo(
                internacoesCardiovasculares,
                "Internações cardiovasculares"
        );

        this.obitosCardiovasculares = validarValorNaoNegativo(
                obitosCardiovasculares,
                "Óbitos cardiovasculares"
        );

        this.procedimentosCardiovasculares = validarValorNaoNegativo(
                procedimentosCardiovasculares,
                "Procedimentos cardiovasculares"
        );

        this.fonte = fonte;
        this.registradoEm = registradoEm == null
                ? LocalDateTime.now()
                : registradoEm;
    }

    public static IndicadorCardiovascular cadastrar(
            UUID municipioId,
            YearMonth competencia,
            int populacaoEstimada,
            int populacaoIdosa,
            int atendimentosHipertensao,
            int atendimentosDiabetes,
            int internacoesCardiovasculares,
            int obitosCardiovasculares,
            int procedimentosCardiovasculares,
            FonteDado fonte
    ) {
        return new IndicadorCardiovascular(
                UUID.randomUUID(),
                municipioId,
                competencia,
                populacaoEstimada,
                populacaoIdosa,
                atendimentosHipertensao,
                atendimentosDiabetes,
                internacoesCardiovasculares,
                obitosCardiovasculares,
                procedimentosCardiovasculares,
                fonte,
                LocalDateTime.now()
        );
    }

    public double taxaInternacoesPorCemMilHabitantes() {
        return calcularTaxaPorCemMil(
                internacoesCardiovasculares
        );
    }

    public double taxaObitosPorCemMilHabitantes() {
        return calcularTaxaPorCemMil(
                obitosCardiovasculares
        );
    }

    public double taxaAtendimentosHipertensaoPorCemMilHabitantes() {
        return calcularTaxaPorCemMil(
                atendimentosHipertensao
        );
    }

    public double taxaAtendimentosDiabetesPorCemMilHabitantes() {
        return calcularTaxaPorCemMil(
                atendimentosDiabetes
        );
    }

    public double taxaProcedimentosPorCemMilHabitantes() {
        return calcularTaxaPorCemMil(
                procedimentosCardiovasculares
        );
    }

    public double percentualPopulacaoIdosa() {
        return populacaoIdosa * 100.0 / populacaoEstimada;
    }

    public int cargaAssistencialTotal() {
        return atendimentosHipertensao
                + atendimentosDiabetes
                + internacoesCardiovasculares
                + procedimentosCardiovasculares;
    }

    public int totalInternacoesCardiovasculares() {
        return internacoesCardiovasculares;
    }

    private double calcularTaxaPorCemMil(
            int quantidade
    ) {
        return quantidade * 100_000.0 / populacaoEstimada;
    }

    private int validarValorNaoNegativo(
            int valor,
            String campo
    ) {
        if (valor < 0) {
            throw new IllegalArgumentException(
                    campo + " não pode ser negativo."
            );
        }

        return valor;
    }

    private void validarId(
            UUID valor,
            String mensagem
    ) {
        if (valor == null) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getMunicipioId() {
        return municipioId;
    }

    public YearMonth getCompetencia() {
        return competencia;
    }

    public int getPopulacaoEstimada() {
        return populacaoEstimada;
    }

    public int getPopulacaoIdosa() {
        return populacaoIdosa;
    }

    public int getAtendimentosHipertensao() {
        return atendimentosHipertensao;
    }

    public int getAtendimentosDiabetes() {
        return atendimentosDiabetes;
    }

    public int getInternacoesCardiovasculares() {
        return internacoesCardiovasculares;
    }

    public int getObitosCardiovasculares() {
        return obitosCardiovasculares;
    }

    public int getProcedimentosCardiovasculares() {
        return procedimentosCardiovasculares;
    }

    public FonteDado getFonte() {
        return fonte;
    }

    public LocalDateTime getRegistradoEm() {
        return registradoEm;
    }
}