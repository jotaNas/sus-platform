package com.fiap.domain.dataaccess;

import com.fiap.domain.model.Alerta;
import com.fiap.domain.vo.StatusAlerta;
import com.fiap.domain.vo.TipoAlerta;

import java.util.List;
import java.util.Optional;

public interface AlertaDataAccess {

    void salvar(Alerta alerta);

    Optional<Alerta> buscarAbertoPorTipoUnidadeMedicamento(
            TipoAlerta tipo,
            String unidadeId,
            String medicamentoId
    );

    List<Alerta> listarPorStatus(StatusAlerta status);

    List<Alerta> listarPorEstoqueId(String estoqueId);
}
