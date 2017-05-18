package br.ufg.inf.produto;

import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;
import java.util.*;

public class Estoque {

	private static Map<Produto, Integer> produtosEmEstoque = new HashMap<Produto, Integer>();

	private static Estoque estoque = new Estoque();

	public static Estoque Instancia() {
		return estoque;
	}

	public void adicionar(Produto produto, int quantidade) {					

			if (produtosEmEstoque.containsKey(produto)) {
				int qtdAnterior = produtosEmEstoque.get(produto);
				produtosEmEstoque.put(produto, qtdAnterior + quantidade);
			} else {
				
				produtosEmEstoque.put(produto, quantidade);
			}		


			LogEstoque.getInstancia().adicionar(EnumTipoDeOperacao.ADICIONAR, produto, quantidade);		
	}

	public void remover(Produto produto, int quantidade) {

		if (produtosEmEstoque.containsKey(produto)) {
			int quantidadeAtual = produtosEmEstoque.get(produto);

			if (quantidadeAtual >= quantidade) {
				produtosEmEstoque.put(produto, quantidadeAtual - quantidade);

				LogEstoque.getInstancia().adicionar(EnumTipoDeOperacao.REMOVER, produto, -quantidade);

			} else
				System.out.println("Não existe produto em quantidade suficiente em estoque!");

		} else {
			System.out.println("Não existe produto no estoque");
		}
	}

	public Map<Produto, Integer> estoqueProdutos() {
		return produtosEmEstoque;
	}

	public Produto obtenhaProduto(int codigo) {
		for (Map.Entry<Produto, Integer> produto : produtosEmEstoque.entrySet()) {
			if (produto.getKey().getCodigo() == codigo) {
				return produto.getKey();
			}
		}

		return null;
	}
}
