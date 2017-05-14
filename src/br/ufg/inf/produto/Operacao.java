package br.ufg.inf.produto;

import java.time.LocalDate;

/**
 * Created by vinicius on 14/05/17.
 */
public class Operacao {

   // final static int ADICIONAR  = 0;
   // final static int REMOVER = 1;


    int tipooperacao;
    Produto produto;
    int qtd_produto;
    LocalDate dataOperacao;

    public Operacao(int tipooperacao, Produto produto, int qtd_produto, LocalDate dataOperacao) {
        this.tipooperacao = tipooperacao;
        this.produto = produto;
        this.qtd_produto = qtd_produto;
        this.dataOperacao = dataOperacao;
    }

    public int getTipooperacao() {
        return tipooperacao;
    }

    public void setTipooperacao(int tipooperacao) {
        this.tipooperacao = tipooperacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtd_produto() {
        return qtd_produto;
    }

    public void setQtd_produto(int qtd_produto) {
        this.qtd_produto = qtd_produto;
    }

    public LocalDate getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(LocalDate dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}
