package br.ufg.inf.venda;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.pessoa.Funcionario;

public class Caixa {
	private List<Venda> vendas;
	private Funcionario funcionario;
	private String identificador;
	
	public Caixa(String identificador){
		this.identificador = identificador;
		this.vendas = new ArrayList<Venda>();
	}
	
	public void registrarVenda(Venda venda){
		vendas.add(venda);
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public String getIdentificador() {
		return identificador;
	}
}
