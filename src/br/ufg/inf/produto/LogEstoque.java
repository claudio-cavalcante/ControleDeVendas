package br.ufg.inf.produto;

import java.time.LocalDate;
import java.util.*;

public class LogEstoque {

	private List<Operacao> operacoes;

	private static LogEstoque log = new LogEstoque();

	private LogEstoque() {
	}

	public static LogEstoque getInstancia() {
		return log;
	}

	public void adicionar(EnumTipoDeOperacao tipoDeOperacao, Produto produto, int quantidade) {
		Operacao operacao = new Operacao(tipoDeOperacao, produto, quantidade, LocalDate.now());
		operacoes.add(operacao);
	}

	public Map<Produto, Integer> estoqueInicioDoDia() {
		Map<Produto, Integer> listaProduto = new HashMap<Produto, Integer>();

		LocalDate agora = LocalDate.now();

		operacoes.stream()
				.filter(c -> c.getDataOperacao().compareTo(agora) == -1)
				.forEach(c -> listaProduto.put(c.getProduto(), c.getQtd_produto()));

		return listaProduto;
	}
	
	public Map<Produto, Integer> estoqueFinalDoDia() {
		Map<Produto, Integer> listaProduto = new HashMap<Produto, Integer>();

		LocalDate agora = LocalDate.now();

		operacoes.stream()
				.filter(c -> c.getDataOperacao().compareTo(agora) <= 0)
				.forEach(c -> listaProduto.put(c.getProduto(), c.getQtd_produto()));

		return listaProduto;
	}
}
