package com.fiap.domain.model;

public class Medicamento {

    private final String id;
    private String nome;
    private String principioAtivo;
    private String apresentacao;
    private boolean ativo;

    public Medicamento(String id, String nome, String principioAtivo, String apresentacao) {
        validar(id, "Id é obrigatório.");
        validar(nome, "Nome é obrigatório.");
        validar(principioAtivo, "Princípio ativo é obrigatório.");
        validar(apresentacao, "Apresentação é obrigatória.");

        this.id = id;
        this.nome = nome;
        this.principioAtivo = principioAtivo;
        this.apresentacao = apresentacao;
        this.ativo = true;
    }

    public void atualizarDados(String nome, String principioAtivo, String apresentacao) {
        validar(nome, "Nome é obrigatório.");
        validar(principioAtivo, "Princípio ativo é obrigatório.");
        validar(apresentacao, "Apresentação é obrigatória.");

        this.nome = nome;
        this.principioAtivo = principioAtivo;
        this.apresentacao = apresentacao;
    }

    public void inativar() {
        if (!ativo) {
            throw new IllegalStateException("Medicamento já está inativo.");
        }
        this.ativo = false;
    }

    private void validar(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getPrincipioAtivo() { return principioAtivo; }
    public String getApresentacao() { return apresentacao; }
    public boolean isAtivo() { return ativo; }
}