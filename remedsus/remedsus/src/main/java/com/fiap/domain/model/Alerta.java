package com.fiap.domain.model;


import com.fiap.domain.vo.GravidadeAlerta;
import com.fiap.domain.vo.StatusAlerta;
import com.fiap.domain.vo.TipoAlerta;

import java.time.LocalDateTime;

public class Alerta {

    private final String id;
    private final TipoAlerta tipo;
    private final GravidadeAlerta gravidade;
    private final String unidadeId;
    private final String medicamentoId;
    private final String estoqueId;
    private final String loteId;
    private final String mensagem;
    private StatusAlerta status;
    private final LocalDateTime criadoEm;
    private LocalDateTime resolvidoEm;

    public Alerta(
            String id,
            TipoAlerta tipo,
            GravidadeAlerta gravidade,
            String unidadeId,
            String medicamentoId,
            String estoqueId,
            String loteId,
            String mensagem,
            LocalDateTime criadoEm
    ) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Id é obrigatório.");
        if (tipo == null) throw new IllegalArgumentException("Tipo do alerta é obrigatório.");
        if (gravidade == null) throw new IllegalArgumentException("Gravidade é obrigatória.");
        if (unidadeId == null || unidadeId.isBlank()) throw new IllegalArgumentException("Unidade é obrigatória.");
        if (medicamentoId == null || medicamentoId.isBlank()) throw new IllegalArgumentException("Medicamento é obrigatório.");
        if (mensagem == null || mensagem.isBlank()) throw new IllegalArgumentException("Mensagem é obrigatória.");

        this.id = id;
        this.tipo = tipo;
        this.gravidade = gravidade;
        this.unidadeId = unidadeId;
        this.medicamentoId = medicamentoId;
        this.estoqueId = estoqueId;
        this.loteId = loteId;
        this.mensagem = mensagem;
        this.status = StatusAlerta.ABERTO;
        this.criadoEm = criadoEm == null ? LocalDateTime.now() : criadoEm;
    }

    public void resolver() {
        if (status == StatusAlerta.RESOLVIDO) {
            throw new IllegalStateException("Alerta já está resolvido.");
        }

        this.status = StatusAlerta.RESOLVIDO;
        this.resolvidoEm = LocalDateTime.now();
    }

    public void restaurarStatus(StatusAlerta status, LocalDateTime resolvidoEm) {
        this.status = status;
        this.resolvidoEm = resolvidoEm;
    }

    public String getId() { return id; }
    public TipoAlerta getTipo() { return tipo; }
    public GravidadeAlerta getGravidade() { return gravidade; }
    public String getUnidadeId() { return unidadeId; }
    public String getMedicamentoId() { return medicamentoId; }
    public String getEstoqueId() { return estoqueId; }
    public String getLoteId() { return loteId; }
    public String getMensagem() { return mensagem; }
    public StatusAlerta getStatus() { return status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public LocalDateTime getResolvidoEm() { return resolvidoEm; }
}
