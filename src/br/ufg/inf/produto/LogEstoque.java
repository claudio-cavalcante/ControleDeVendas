package br.ufg.inf.produto;

import java.time.LocalDate;
import java.util.*;

public class LogEstoque {

	private List<Operacao> operacoes;

	private static LogEstoque log = new LogEstoque();

	private LogEstoque() {
		operacoes = new ArrayList<Operacao>();
	}

	public static LogEstoque getInstancia() {
		return log;
	}

	public void adicionar(EnumTipoDeOperacao tipoDeOperacao, Produto produto, int quantidade) {
		Operacao operacao = new Operacao(tipoDeOperacao, produto, quantidade, LocalDate.now());
		operacoes.add(operacao);
	}
	
	public List<Operacao> getOperacoes(){
		return operacoes;
	}
}
