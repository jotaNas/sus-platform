package com.fiap.cardioradar.application.handler;

import com.fiap.cardioradar.application.command.CadastrarMunicipioCommand;
import com.fiap.cardioradar.domain.MunicipioMonitorado;
import com.fiap.cardioradar.domain.dataaccess.MunicipioMonitoradoDataAccess;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
public class CadastrarMunicipioHandler {

    private final MunicipioMonitoradoDataAccess municipioDataAccess;

    public CadastrarMunicipioHandler(
            MunicipioMonitoradoDataAccess municipioDataAccess
    ) {
        this.municipioDataAccess = municipioDataAccess;
    }

    @Transactional
    public UUID handle(CadastrarMunicipioCommand command) {
        validarCommand(command);

        String codigoIbge = command.codigoIbge().trim();
        String nome = command.nome().trim();
        String uf = command.uf()
                .trim()
                .toUpperCase(Locale.ROOT);

        if (municipioDataAccess.existePorCodigoIbge(codigoIbge)) {
            throw new IllegalStateException(
                    "Já existe um município cadastrado com o código IBGE "
                            + codigoIbge + "."
            );
        }

        MunicipioMonitorado municipio =
                MunicipioMonitorado.criar(
                        codigoIbge,
                        nome,
                        uf
                );

        municipioDataAccess.salvar(municipio);

        return municipio.getId();
    }

    private void validarCommand(
            CadastrarMunicipioCommand command
    ) {
        Objects.requireNonNull(
                command,
                "Comando de cadastro de município é obrigatório."
        );
    }
}