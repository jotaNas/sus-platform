package com.fiap.cardioradar.domain;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class MunicipioMonitorado {

    private final UUID id;
    private final String codigoIbge;
    private final String nome;
    private final String uf;
    private boolean ativo;
    private final LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public MunicipioMonitorado(
            UUID id,
            String codigoIbge,
            String nome,
            String uf,
            boolean ativo,
            LocalDateTime criadoEm,
            LocalDateTime atualizadoEm
    ) {
        validarId(id);
        validarCodigoIbge(codigoIbge);
        validarNome(nome);
        validarUf(uf);

        this.id = id;
        this.codigoIbge = codigoIbge.trim();
        this.nome = nome.trim();
        this.uf = uf.trim().toUpperCase(Locale.ROOT);
        this.ativo = ativo;
        this.criadoEm = criadoEm == null
                ? LocalDateTime.now()
                : criadoEm;
        this.atualizadoEm = atualizadoEm == null
                ? this.criadoEm
                : atualizadoEm;
    }

    public static MunicipioMonitorado criar(
            String codigoIbge,
            String nome,
            String uf
    ) {
        LocalDateTime agora = LocalDateTime.now();

        return new MunicipioMonitorado(
                UUID.randomUUID(),
                codigoIbge,
                nome,
                uf,
                true,
                agora,
                agora
        );
    }

    public void inativar() {
        if (!ativo) {
            return;
        }

        ativo = false;
        atualizadoEm = LocalDateTime.now();
    }

    public void ativar() {
        if (ativo) {
            return;
        }

        ativo = true;
        atualizadoEm = LocalDateTime.now();
    }

    private void validarId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Id do município é obrigatório."
            );
        }
    }

    private void validarCodigoIbge(String codigoIbge) {
        if (codigoIbge == null || !codigoIbge.matches("\\d{7}")) {
            throw new IllegalArgumentException(
                    "Código IBGE deve possuir exatamente 7 números."
            );
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "Nome do município é obrigatório."
            );
        }

        String nomeNormalizado = nome.trim();

        if (nomeNormalizado.length() < 2
                || nomeNormalizado.length() > 150) {
            throw new IllegalArgumentException(
                    "Nome do município deve possuir entre 2 e 150 caracteres."
            );
        }
    }

    private void validarUf(String uf) {
        if (uf == null || !uf.trim().matches("[A-Za-z]{2}")) {
            throw new IllegalArgumentException(
                    "UF deve possuir exatamente duas letras."
            );
        }
    }

    public UUID getId() {
        return id;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }
}