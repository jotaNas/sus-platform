package com.fiap.cardioradar.application.handler;

import com.fiap.cardioradar.application.command.CadastrarIndicadorCardiovascularCommand;
import com.fiap.cardioradar.domain.IndicadorCardiovascular;
import com.fiap.cardioradar.domain.dataaccess.IndicadorCardiovascularDataAccess;
import com.fiap.cardioradar.domain.dataaccess.MunicipioMonitoradoDataAccess;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;


@ApplicationScoped
public class CadastrarIndicadorCardiovascularHandler {

    private final IndicadorCardiovascularDataAccess indicadorDataAccess;
    private final MunicipioMonitoradoDataAccess municipioDataAccess;

    public CadastrarIndicadorCardiovascularHandler(
            IndicadorCardiovascularDataAccess indicadorDataAccess,
            MunicipioMonitoradoDataAccess municipioDataAccess
    ) {
        this.indicadorDataAccess = indicadorDataAccess;
        this.municipioDataAccess = municipioDataAccess;
    }

    @Transactional
    public UUID handle(
            CadastrarIndicadorCardiovascularCommand command
    ) {
        validarCommand(command);

        validarMunicipioExistente(command.municipioId());

        validarIndicadorDuplicado(command);

        IndicadorCardiovascular indicador =
                IndicadorCardiovascular.cadastrar(
                        command.municipioId(),
                        command.competencia(),
                        command.populacaoEstimada(),
                        command.populacaoIdosa(),
                        command.atendimentosHipertensao(),
                        command.atendimentosDiabetes(),
                        command.internacoesCardiovasculares(),
                        command.obitosCardiovasculares(),
                        command.procedimentosCardiovasculares(),
                        command.fonte()
                );

        indicadorDataAccess.salvar(indicador);

        return indicador.getId();
    }

    private void validarMunicipioExistente(
            UUID municipioId
    ) {
        municipioDataAccess.buscarPorId(municipioId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Município não encontrado: "
                                        + municipioId
                        )
                );
    }

    private void validarIndicadorDuplicado(
            CadastrarIndicadorCardiovascularCommand command
    ) {
        boolean existente =
                indicadorDataAccess
                        .existePorMunicipioECompetencia(
                                command.municipioId(),
                                command.competencia()
                        );

        if (existente) {
            throw new IllegalStateException(
                    "Já existe um indicador cardiovascular "
                            + "para o município "
                            + command.municipioId()
                            + " na competência "
                            + command.competencia()
                            + "."
            );
        }
    }

    private void validarCommand(
            CadastrarIndicadorCardiovascularCommand command
    ) {
        if (command == null) {
            throw new IllegalArgumentException(
                    "Os dados do indicador são obrigatórios."
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

        if (command.fonte() == null) {
            throw new IllegalArgumentException(
                    "A fonte do dado é obrigatória."
            );
        }
    }
}