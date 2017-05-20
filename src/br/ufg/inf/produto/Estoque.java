package br.ufg.inf.produto;

import java.util.*;

import br.ufg.inf.exception.EstoqueInsuficienteException;
import br.ufg.inf.exception.ProdutoNaoCadastradoException;
import br.ufg.inf.menu.MensagensSistema;

public class Estoque {

	private static Map<Produto, Float> produtosEmEstoque = new HashMap<Produto, Float>();

	private static Estoque estoque = new Estoque();

	public static Estoque Instancia() {
		return estoque;
	}

	public boolean adicionar(Produto produto, float quantidade) {
		if (getProduto(produto.getCodigo()) == null) {
			produtosEmEstoque.put(produto, quantidade);
			LogEstoque.getInstancia().adicionar(EnumTipoDeOperacao.ADICIONAR, produto, quantidade);
			return true;
		}
		
		return false;
	}

	public void remover(Produto produto, float quantidade) throws EstoqueInsuficienteException, ProdutoNaoCadastradoException {
		if (produtosEmEstoque.containsKey(produto)) {
			float quantidadeAtual = produtosEmEstoque.get(produto);
			if (quantidadeAtual >= quantidade) {
				produtosEmEstoque.put(produto, quantidadeAtual - quantidade);
				LogEstoque.getInstancia().adicionar(EnumTipoDeOperacao.REMOVER, produto, -quantidade);
			} else
				throw new EstoqueInsuficienteException();
		} else {
			throw new ProdutoNaoCadastradoException();
		}
	}

	public void repor(int codigo, float quantidade) {
		Produto produto = getProduto(codigo);
		if (produto != null) {
			float quantidadeAnterior = produtosEmEstoque.get(produto);
			produtosEmEstoque.put(produto, quantidadeAnterior + quantidade);
			LogEstoque.getInstancia().adicionar(EnumTipoDeOperacao.REPOR, produto, quantidade);
		}
	}

	public Map<Produto, Float> estoqueProdutos() {
		return produtosEmEstoque;
	}

	public float getQuantidadeEmEstoque(int codigo) {
		Produto produto = getProduto(codigo);
		if (produto == null) {
			return 0;
		} else {
			return produtosEmEstoque.get(produto);
		}
	}

	public Produto getProduto(int codigo) {
		for (Map.Entry<Produto, Float> produto : produtosEmEstoque.entrySet()) {
			if (produto.getKey().getCodigo() == codigo) {
				return produto.getKey();
			}
		}

		return null;
	}
}
