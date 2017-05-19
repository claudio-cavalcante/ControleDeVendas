package br.ufg.inf.produto;

import java.time.LocalDate;

public class Operacao {

	private EnumTipoDeOperacao tipooperacao;
	private Produto produto;
	private float qtd_produto;
	private LocalDate dataOperacao;

    public Operacao(EnumTipoDeOperacao tipooperacao, Produto produto, float qtdProduto, LocalDate dataOperacao) {
        this.tipooperacao = tipooperacao;
        this.produto = produto;
        this.qtd_produto = qtdProduto;
        this.dataOperacao = dataOperacao;
    }

    public EnumTipoDeOperacao getTipooperacao() {
        return tipooperacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public float getQtdProduto() {
        return qtd_produto;
    }

    public LocalDate getDataOperacao() {
        return dataOperacao;
    }
}
