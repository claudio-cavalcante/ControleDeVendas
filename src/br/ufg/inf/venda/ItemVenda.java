package br.ufg.inf.venda;

import br.ufg.inf.produto.Produto;

public class ItemVenda {
	private Produto produto;
	
	private float quantidade;
	
	public ItemVenda(Produto produto, float quantidade){
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public float getQuantidade() {
		return quantidade;
	}
	
	public double getValorTotal(){
		return produto.getPreco() * quantidade;
	}
}
