package br.com.lucasteixeira.vilacontrol.enums;

public enum StatusPagamento {

    PENDENTE("Pendente"),
    RECEBIDO("Recebido");

    private String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
