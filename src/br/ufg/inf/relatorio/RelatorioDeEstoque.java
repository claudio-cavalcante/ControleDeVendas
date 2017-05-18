package br.ufg.inf.relatorio;

import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Operacao;
import br.ufg.inf.produto.Produto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RelatorioDeEstoque implements Relatorio {

	public List<Produto> estoqueAtual() {

		List<Produto> listaproduto = new ArrayList<>();

		Set<Map.Entry<Produto, Integer>> set = Estoque.Instancia().estoqueProdutos().entrySet();

		for (Map.Entry<Produto, Integer> produto : set) {
			listaproduto.add(produto.getKey());

		}

		return listaproduto;
	}

	@Override
	public String emitir() {

		String relatorio = "";

		Map<Produto, Integer> produtosEmEstoque = Estoque.Instancia().estoqueProdutos();

		for (Map.Entry<Produto, Integer> entrySetProduto : produtosEmEstoque.entrySet()) {

			Produto produto = entrySetProduto.getKey();
			int quantidade = entrySetProduto.getValue();

			relatorio += String.format(
					"Código do produto: %s\nDescrição do Produto: %s\nPreço: %.2f\nQuantidade: %d\n\n",
					produto.getCodigo(), produto.getDescricao(), produto.getPreco(), quantidade);
		}

		return relatorio;
	}
}
