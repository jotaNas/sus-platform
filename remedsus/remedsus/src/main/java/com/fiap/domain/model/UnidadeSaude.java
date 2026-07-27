package com.fiap.domain.model;

public class UnidadeSaude {

    private final String id;
    private String nome;
    private TipoUnidadeSaude tipo;
    private String municipio;
    private String bairro;
    private boolean ativa;

    public UnidadeSaude(
            String id,
            String nome,
            TipoUnidadeSaude tipo,
            String municipio,
            String bairro
    ) {
        validar(id, "Id é obrigatório.");
        validar(nome, "Nome é obrigatório.");
        validar(municipio, "Município é obrigatório.");
        validar(bairro, "Bairro é obrigatório.");

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo da unidade é obrigatório.");
        }

        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.municipio = municipio;
        this.bairro = bairro;
        this.ativa = true;
    }

    public void atualizarDados(String nome, TipoUnidadeSaude tipo, String municipio, String bairro) {
        validar(nome, "Nome é obrigatório.");
        validar(municipio, "Município é obrigatório.");
        validar(bairro, "Bairro é obrigatório.");

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo da unidade é obrigatório.");
        }

        this.nome = nome;
        this.tipo = tipo;
        this.municipio = municipio;
        this.bairro = bairro;
    }

    public void inativar() {
        if (!ativa) {
            throw new IllegalStateException("Unidade já está inativa.");
        }
        this.ativa = false;
    }

    private void validar(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public TipoUnidadeSaude getTipo() { return tipo; }
    public String getMunicipio() { return municipio; }
    public String getBairro() { return bairro; }
    public boolean isAtiva() { return ativa; }
}