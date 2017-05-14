package br.ufg.inf.venda;

import br.ufg.inf.produto.Produto;

public class ItemVenda {
	private Produto produto;
	
	private int quantidade;
	
	public ItemVenda(Produto produto, int quantidade){
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public double getValorTotal(){
		return produto.getPreco() * quantidade;
	}
}
