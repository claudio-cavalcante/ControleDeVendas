package br.ufg.inf.relatorio;

import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.LogEstoque;
import br.ufg.inf.produto.Operacao;
import br.ufg.inf.produto.Produto;
import java.time.LocalDate;
import java.util.*;

public class RelatorioDeEstoque implements Relatorio {

	public List<Produto> estoqueAtual() {
		List<Produto> listaproduto = new ArrayList<>();
		Set<Map.Entry<Produto, Float>> set = Estoque.Instancia().estoqueProdutos().entrySet();
		for (Map.Entry<Produto, Float> produto : set) {
			listaproduto.add(produto.getKey());
		}

		return listaproduto;
	}

	@Override
	public String emitir() {
		String relatorio = MensagensSistemaDeVendas.MARCARDOR_RELATORIO + "\n";
		relatorio += MensagensSistemaDeVendas.RELATORIO_ESTOQUE + "\n";
		Map<Produto, Float> produtosEmEstoque = Estoque.Instancia().estoqueProdutos();
		for (Map.Entry<Produto, Float> entrySetProduto : produtosEmEstoque.entrySet()) {
			Produto produto = entrySetProduto.getKey();
			float quantidade = entrySetProduto.getValue();

			relatorio += String.format(
					"Código do produto: %s\nDescrição do Produto: %s\nPreço: R$%.2f\nQuantidade: %.2f\n\n",
					produto.getCodigo(), produto.getDescricao(), produto.getPreco(), quantidade);
		}

		relatorio += MensagensSistemaDeVendas.MARCARDOR_RELATORIO + "\n";
		return relatorio;
	}

	public Map<Produto, Float> estoqueInicioDoDia() {
		Map<Produto, Float> estoqueDoInicioDoDia = new HashMap<Produto, Float>();

		LocalDate agora = LocalDate.now();
		Set<Map.Entry<Produto, Float>> estoqueAtual = Estoque.Instancia().estoqueProdutos().entrySet();

		for (Map.Entry<Produto, Float> estoque : estoqueAtual) {
			float quantidadeAtual = estoque.getValue().intValue();

			estoqueDoInicioDoDia.put(estoque.getKey(), quantidadeAtual);

			for (Operacao operacao : LogEstoque.getInstancia().getOperacoes()) {
				if (operacao.getDataOperacao().compareTo(agora) != 0) {
					continue;
				}

				if (operacao.getProduto().equals(estoque.getKey())) {
					quantidadeAtual = quantidadeAtual - operacao.getQtdProduto();

					estoqueDoInicioDoDia.put(estoque.getKey(), quantidadeAtual);
				}
			}
		}

		return estoqueDoInicioDoDia;
	}

	public Map<Produto, Float> estoqueFinalDoDia() {
		return Estoque.Instancia().estoqueProdutos();
	}
}
