package br.ufg.inf.produto;

import java.time.LocalDate;

public class Operacao {

	private EnumTipoDeOperacao tipooperacao;
	private Produto produto;
	private int qtd_produto;
	private LocalDate dataOperacao;

    public Operacao(EnumTipoDeOperacao tipooperacao, Produto produto, int qtd_produto, LocalDate dataOperacao) {
        this.tipooperacao = tipooperacao;
        this.produto = produto;
        this.qtd_produto = qtd_produto;
        this.dataOperacao = dataOperacao;
    }

    public EnumTipoDeOperacao getTipooperacao() {
        return tipooperacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQtd_produto() {
        return qtd_produto;
    }

    public LocalDate getDataOperacao() {
        return dataOperacao;
    }
}
