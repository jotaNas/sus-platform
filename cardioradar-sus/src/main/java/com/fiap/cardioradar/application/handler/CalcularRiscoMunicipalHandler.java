package com.fiap.cardioradar.application.handler;


import com.fiap.cardioradar.application.command.CalcularRiscoMunicipalCommand;
import com.fiap.cardioradar.domain.IndicadorCardiovascular;
import com.fiap.cardioradar.domain.RiscoMunicipal;
import com.fiap.cardioradar.domain.dataaccess.IndicadorCardiovascularDataAccess;
import com.fiap.cardioradar.domain.dataaccess.RiscoMunicipalDataAccess;
import com.fiap.cardioradar.domain.event.RiscoCardiovascularMunicipalCalculadoEvent;
import com.fiap.cardioradar.domain.service.CalculadoraIndicePressaoCardiovascular;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.transaction.Transactional;

import com.fiap.cardioradar.domain.PressaoMedicamento;

@ApplicationScoped
public class CalcularRiscoMunicipalHandler {

    private final IndicadorCardiovascularDataAccess indicadorDataAccess;
    private final RiscoMunicipalDataAccess riscoMunicipalDataAccess;
    private final CalculadoraIndicePressaoCardiovascular calculadora;
    private final Event<RiscoCardiovascularMunicipalCalculadoEvent>
            eventoCalculado;

    public CalcularRiscoMunicipalHandler(
            IndicadorCardiovascularDataAccess indicadorDataAccess,
            RiscoMunicipalDataAccess riscoMunicipalDataAccess,
            CalculadoraIndicePressaoCardiovascular calculadora,
            Event<RiscoCardiovascularMunicipalCalculadoEvent>
                    eventoCalculado
    ) {
        this.indicadorDataAccess = indicadorDataAccess;
        this.riscoMunicipalDataAccess = riscoMunicipalDataAccess;
        this.calculadora = calculadora;
        this.eventoCalculado = eventoCalculado;
    }

    @Transactional
    public RiscoMunicipal handle(
            CalcularRiscoMunicipalCommand command
    ) {
        validarCommand(command);

        validarRiscoNaoCalculado(command);

        IndicadorCardiovascular indicador =
                buscarIndicador(command);

        PressaoMedicamento pressao =
                command.pressaoMedicamento();

        RiscoMunicipal riscoAnterior =
                buscarUltimoRiscoDoMunicipio(command);

        RiscoMunicipal riscoMunicipal =
                calculadora.calcular(
                        indicador,
                        pressao,
                        riscoAnterior
                );

        riscoMunicipalDataAccess.salvar(
                riscoMunicipal
        );

        publicarEvento(
                riscoMunicipal
        );

        return riscoMunicipal;
    }

    private IndicadorCardiovascular buscarIndicador(
            CalcularRiscoMunicipalCommand command
    ) {
        return indicadorDataAccess
                .buscarPorMunicipioECompetencia(
                        command.municipioId(),
                        command.competencia()
                )
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Indicador cardiovascular não encontrado "
                                        + "para o município "
                                        + command.municipioId()
                                        + " na competência "
                                        + command.competencia()
                                        + "."
                        )
                );
    }

    private RiscoMunicipal buscarUltimoRiscoDoMunicipio(
            CalcularRiscoMunicipalCommand command
    ) {
        return riscoMunicipalDataAccess
                .buscarUltimoPorMunicipio(
                        command.municipioId()
                )
                .orElse(null);
    }

    private void validarRiscoNaoCalculado(
            CalcularRiscoMunicipalCommand command
    ) {
        boolean riscoJaCalculado =
                riscoMunicipalDataAccess
                        .buscarPorMunicipioECompetencia(
                                command.municipioId(),
                                command.competencia()
                        )
                        .isPresent();

        if (riscoJaCalculado) {
            throw new IllegalStateException(
                    "O risco cardiovascular do município "
                            + command.municipioId()
                            + " já foi calculado para a competência "
                            + command.competencia()
                            + "."
            );
        }
    }

    private void publicarEvento(
            RiscoMunicipal risco
    ) {
        RiscoCardiovascularMunicipalCalculadoEvent evento =
                new RiscoCardiovascularMunicipalCalculadoEvent(
                        risco.getId().toString(),
                        risco.getMunicipioId().toString(),
                        risco.getCompetencia(),
                        risco.getIndicePressaoCardiovascular(),
                        risco.getNivel(),
                        risco.getTendencia()
                );

        eventoCalculado.fire(evento);
    }

    private void validarCommand(
            CalcularRiscoMunicipalCommand command
    ) {
        if (command == null) {
            throw new IllegalArgumentException(
                    "Os dados para o cálculo do risco são obrigatórios."
            );
        }

        if (command.municipioId() == null) {
            throw new IllegalArgumentException(
                    "O município é obrigatório."
            );
        }

        if (command.competencia() == null) {
            throw new IllegalArgumentException(
                    "A competência é obrigatória."
            );
        }

        if (command.pressaoMedicamento() == null) {
            throw new IllegalArgumentException(
                    "A pressão do medicamento é obrigatória."
            );
        }
    }
}