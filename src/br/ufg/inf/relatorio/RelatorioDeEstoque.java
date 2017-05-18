package br.ufg.inf.relatorio;

import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.LogEstoque;
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
	
	public Map<Produto, Integer> estoqueInicioDoDia() {
		Map<Produto, Integer> listaProduto = new HashMap<Produto, Integer>();

		LocalDate agora = LocalDate.now();

		LogEstoque.getInstancia().getOperacoes().stream()
				.filter(c -> c.getDataOperacao().compareTo(agora) == -1)
				.forEach(c -> listaProduto.put(c.getProduto(), c.getQtd_produto()));

		return listaProduto;
	}
	
	public Map<Produto, Integer> estoqueFinalDoDia() {
		Map<Produto, Integer> listaProduto = new HashMap<Produto, Integer>();

		LocalDate agora = LocalDate.now();

		LogEstoque.getInstancia().getOperacoes().stream()
				.filter(c -> c.getDataOperacao().compareTo(agora) <= 0)
				.forEach(c -> listaProduto.put(c.getProduto(), c.getQtd_produto()));

		return listaProduto;
	}
}
