package br.ufg.inf.produto;

public class Produto {
	private double preco;
	private String descricao;
	
	public Produto(double preco, String descricao){
		this.preco = preco;
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
