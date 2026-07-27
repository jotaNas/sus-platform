package com.fiap.infrastructure.persistence.jpa.entity;

import com.fiap.domain.vo.GravidadeAlerta;
import com.fiap.domain.vo.StatusAlerta;
import com.fiap.domain.vo.TipoAlerta;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
public class AlertaJPAEntity extends PanacheEntityBase {

    @Id
    public String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TipoAlerta tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public GravidadeAlerta gravidade;

    @Column(name = "unidade_id", nullable = false)
    public String unidadeId;

    @Column(name = "medicamento_id", nullable = false)
    public String medicamentoId;

    @Column(name = "estoque_id")
    public String estoqueId;

    @Column(name = "lote_id")
    public String loteId;

    @Column(nullable = false, length = 500)
    public String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public StatusAlerta status;

    @Column(name = "criado_em", nullable = false)
    public LocalDateTime criadoEm;

    @Column(name = "resolvido_em")
    public LocalDateTime resolvidoEm;
}
