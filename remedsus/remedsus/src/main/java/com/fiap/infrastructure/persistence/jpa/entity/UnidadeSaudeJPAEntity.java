package com.fiap.infrastructure.persistence.jpa.entity;

import com.fiap.domain.model.TipoUnidadeSaude;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(
        name = "unidades_saude",
        indexes = {
                @Index(name = "idx_unidade_municipio", columnList = "municipio"),
                @Index(name = "idx_unidade_tipo", columnList = "tipo")
        }
)
public class UnidadeSaudeJPAEntity extends PanacheEntityBase {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoUnidadeSaude tipo;

    @Column(name = "municipio", nullable = false, length = 120)
    private String municipio;

    @Column(name = "bairro", nullable = false, length = 120)
    private String bairro;

    @Column(name = "ativa", nullable = false)
    private boolean ativa;

    protected void UnidadeSaudeEntity() {
        // JPA
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUnidadeSaude getTipo() {
        return tipo;
    }

    public void setTipo(TipoUnidadeSaude tipo) {
        this.tipo = tipo;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
