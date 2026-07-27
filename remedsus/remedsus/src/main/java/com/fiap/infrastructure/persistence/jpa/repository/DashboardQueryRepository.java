package com.fiap.infrastructure.persistence.jpa.repository;

import com.fiap.domain.vo.StatusAlerta;
import com.fiap.domain.vo.TipoAlerta;
import com.fiap.domain.vo.TipoMovimentacaoEstoque;
import com.fiap.infrastructure.api.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class DashboardQueryRepository {

    private final EntityManager entityManager;

    public DashboardQueryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public long totalAlertasAbertos() {
        return entityManager.createQuery("""
                select count(a.id)
                from AlertaJPAEntity a
                where a.status = :status
                """, Long.class)
                .setParameter("status", StatusAlerta.ABERTO)
                .getSingleResult();
    }

    public long totalEstoquesZerados() {
        return entityManager.createQuery("""
                select count(e.id)
                from EstoqueJPAEntity e
                where e.saldoAtual = 0
                """, Long.class)
                .getSingleResult();
    }

    public List<MedicamentoCriticoResponse> medicamentosCriticos() {
        return entityManager.createQuery("""
            select new com.fiap.infrastructure.api.dto.MedicamentoCriticoResponse(
                a.medicamentoId,
                m.nome,
                a.unidadeId,
                u.nome,
                cast(a.tipo as string),
                cast(a.gravidade as string),
                a.mensagem
            )
            from AlertaJPAEntity a
            join MedicamentoJPAEntity m on m.id = a.medicamentoId
            join UnidadeSaudeJPAEntity u on u.id = a.unidadeId
            where a.status = :status
              and a.tipo in (:tipos)
            order by a.criadoEm desc
            """, MedicamentoCriticoResponse.class)
                .setParameter("status", StatusAlerta.ABERTO)
                .setParameter("tipos", List.of(TipoAlerta.ESTOQUE_MINIMO, TipoAlerta.ESTOQUE_ZERADO))
                .setMaxResults(10)
                .getResultList();
    }

    public List<EstoqueZeradoResponse> estoquesZerados() {
        return entityManager.createQuery("""
            select new com.fiap.infrastructure.api.dto.EstoqueZeradoResponse(
                e.id,
                e.unidadeId,
                u.nome,
                e.medicamentoId,
                m.nome
            )
            from EstoqueJPAEntity e
            join MedicamentoJPAEntity m on m.id = e.medicamentoId
            join UnidadeSaudeJPAEntity u on u.id = e.unidadeId
            where e.saldoAtual = 0
            """, EstoqueZeradoResponse.class)
                .setMaxResults(20)
                .getResultList();
    }

    public List<LoteVencendoResponse> lotesVencendo() {
        return entityManager.createQuery("""
                select new com.fiap.infrastructure.api.dto.LoteVencendoResponse(
                    l.id,
                    l.estoque.id,
                    l.medicamentoId,
                    l.validade,
                    l.quantidadeAtual
                )
                from LoteJPAEntity l
                where l.quantidadeAtual > 0
                  and l.validade <= :limite
                order by l.validade asc
                """, LoteVencendoResponse.class)
                .setParameter("limite", LocalDate.now().plusDays(30))
                .setMaxResults(20)
                .getResultList();
    }

    public List<TopMedicamentoDispensadoResponse> topMedicamentosDispensados() {
        List<Object[]> rows = entityManager.createQuery("""
            select
                m.medicamentoId,
                med.nome,
                sum(m.quantidade)
            from MovimentacaoEstoqueJPAEntity m
            join MedicamentoJPAEntity med on med.id = m.medicamentoId
            where m.tipo = :tipo
            group by m.medicamentoId, med.nome
            order by sum(m.quantidade) desc
            """, Object[].class)
                .setParameter("tipo", TipoMovimentacaoEstoque.DISPENSACAO)
                .setMaxResults(10)
                .getResultList();

        return rows.stream()
                .map(row -> new TopMedicamentoDispensadoResponse(
                        (String) row[0],
                        (String) row[1],
                        ((Number) row[2]).longValue()
                ))
                .toList();
    }

    public List<ConsumoMensalResponse> consumoMensal() {
        List<Object[]> rows = entityManager.createQuery("""
            select
                function('to_char', m.realizadaEm, 'YYYY-MM'),
                sum(m.quantidade)
            from MovimentacaoEstoqueJPAEntity m
            where m.tipo = :tipo
            group by function('to_char', m.realizadaEm, 'YYYY-MM')
            order by function('to_char', m.realizadaEm, 'YYYY-MM')
            """, Object[].class)
                .setParameter("tipo", TipoMovimentacaoEstoque.DISPENSACAO)
                .getResultList();

        return rows.stream()
                .map(row -> new ConsumoMensalResponse(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .toList();
    }
}