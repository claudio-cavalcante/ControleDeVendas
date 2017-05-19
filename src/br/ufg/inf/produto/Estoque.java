package br.ufg.inf.produto;

import java.util.*;

public class Estoque {

	private static Map<Produto, Float> produtosEmEstoque = new HashMap<Produto, Float>();

	private static Estoque estoque = new Estoque();

	public static Estoque Instancia() {
		return estoque;
	}

	public void adicionar(Produto produto, float quantidade) {
		if (produtosEmEstoque.containsKey(produto)) {
			float qtdAnterior = produtosEmEstoque.get(produto);
			produtosEmEstoque.put(produto, qtdAnterior + quantidade);
		} else {
			produtosEmEstoque.put(produto, quantidade);
		}

		LogEstoque.getInstancia().adicionar(EnumTipoDeOperacao.ADICIONAR, produto, quantidade);
	}

	public void remover(Produto produto, float quantidade) {
		if (produtosEmEstoque.containsKey(produto)) {
			float quantidadeAtual = produtosEmEstoque.get(produto);
			if (quantidadeAtual >= quantidade) {
				produtosEmEstoque.put(produto, quantidadeAtual - quantidade);
				LogEstoque.getInstancia().adicionar(EnumTipoDeOperacao.REMOVER, produto, -quantidade);
			} else
				System.out.println("Não existe produto em quantidade suficiente em estoque!");
		} else {
			System.out.println("Não existe produto no estoque");
		}
	}

	public Map<Produto, Float> estoqueProdutos() {
		return produtosEmEstoque;
	}

	public float getQuantidadeEmEstoque(int codigo) {
		Produto produto = obtenhaProduto(codigo);
		if (produto == null) {
			return 0;
		} else {
			return produtosEmEstoque.get(produto);
		}
	}

	public Produto obtenhaProduto(int codigo) {
		for (Map.Entry<Produto, Float> produto : produtosEmEstoque.entrySet()) {
			if (produto.getKey().getCodigo() == codigo) {
				return produto.getKey();
			}
		}

		return null;
	}
}
