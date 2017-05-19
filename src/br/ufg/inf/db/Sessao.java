package br.ufg.inf.db;

import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.venda.Caixa;

public class Sessao {
	private static Funcionario funcionarioLogado;
	private static Caixa caixaSelecionado;
	
	public static void setCaixaSelecionado(Caixa caixa){
		caixa.setFuncionario(funcionarioLogado);
		caixaSelecionado = caixa;
	}
	
	public static Caixa getCaixaSelecionado(){
		return caixaSelecionado;
	}
	
	public static void setFuncionarioLogado(Funcionario funcionario){
		funcionarioLogado = funcionario;
	}
	
	public static Funcionario getFuncionarioLogado(){
		return funcionarioLogado;
	}
}
