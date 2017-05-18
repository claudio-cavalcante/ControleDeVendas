package br.ufg.inf.produto;

import br.ufg.inf.menu.EnumFuncoesDoSistema;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;
import kotlin.internal.InlineOnly;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by vinicius on 13/05/17.
 */

public class Estoque {

	private static List<Operacao> historicooperacao = new ArrayList<>();

	private static Map<Produto, Integer> produtosEmEstoque = new HashMap<Produto, Integer>();

	private static Estoque estoque = new Estoque();

	public static Estoque Instancia() {
		return estoque;
	}

	public void adicionar(Funcionario funcionario, Produto produto, int qtd) {

		if (funcionario instanceof Gerente) {

			if (produtosEmEstoque.containsKey(produto)) {
				int qtdAnterior = produtosEmEstoque.get(produto);
				produtosEmEstoque.replace(produto, produtosEmEstoque.get(produto),
						(produtosEmEstoque.get(produto) + qtd));
			} else {
				produtosEmEstoque.put(produto, qtd);
			}

			// PARA FINS DE LOG
			LocalDate agora = LocalDate.now();

			Operacao op = new Operacao(1, produto, qtd, agora);
			historicooperacao.add(op);
		} else
			System.out.println("Somente gerente está autorizado para adicionar produto no estoque!");
	}

	public List<Operacao> estoqueProdutosHistorico() {
		return historicooperacao;
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
	
	public void remover(Produto produto, int qtd) {
		LocalDate agora = LocalDate.now();
		if (produtosEmEstoque.containsKey(produto)) {
			if (produtosEmEstoque.get(produto) > qtd) {
				produtosEmEstoque.put(produto, (produtosEmEstoque.get(produto) - qtd));
				Operacao op = new Operacao(0, produto, (produtosEmEstoque.get(produto)), agora);
				historicooperacao.add(op);

			} else
				System.out.println("Não existe produto em quantidade suficiente em estoque!");

		} else
			System.out.println("Não existe produto no estoque");

	}

}
