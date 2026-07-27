package com.fiap.infrastructure.api;

import com.fiap.domain.dataaccess.AlertaDataAccess;
import com.fiap.domain.vo.StatusAlerta;
import com.fiap.infrastructure.api.dto.AlertaResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/v1/alertas")
@Produces(MediaType.APPLICATION_JSON)
public class AlertaResource {

    private final AlertaDataAccess alertaDataAccess;

    public AlertaResource(AlertaDataAccess alertaDataAccess) {
        this.alertaDataAccess = alertaDataAccess;
    }

    @GET
    public List<AlertaResponse> listarAbertos() {
        return alertaDataAccess.listarPorStatus(StatusAlerta.ABERTO)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GET
    @Path("/estoques/{estoqueId}")
    public List<AlertaResponse> listarPorEstoque(@PathParam("estoqueId") String estoqueId) {
        return alertaDataAccess.listarPorEstoqueId(estoqueId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AlertaResponse toResponse(com.fiap.domain.model.Alerta alerta) {
        return new AlertaResponse(
                alerta.getId(),
                alerta.getTipo(),
                alerta.getGravidade(),
                alerta.getUnidadeId(),
                alerta.getMedicamentoId(),
                alerta.getEstoqueId(),
                alerta.getLoteId(),
                alerta.getMensagem(),
                alerta.getStatus(),
                alerta.getCriadoEm(),
                alerta.getResolvidoEm()
        );
    }
}
