package com.fiap.infrastructure.api.dto;

import com.fiap.domain.vo.GravidadeAlerta;
import com.fiap.domain.vo.StatusAlerta;
import com.fiap.domain.vo.TipoAlerta;

import java.time.LocalDateTime;

public record AlertaResponse(
        String id,
        TipoAlerta tipo,
        GravidadeAlerta gravidade,
        String unidadeId,
        String medicamentoId,
        String estoqueId,
        String loteId,
        String mensagem,
        StatusAlerta status,
        LocalDateTime criadoEm,
        LocalDateTime resolvidoEm
) {}
