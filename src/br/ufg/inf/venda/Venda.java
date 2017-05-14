package br.ufg.inf.venda;

import java.util.List;

import br.ufg.inf.pagamento.FormaDePagamento;
import br.ufg.inf.pessoa.Funcionario;

public class Venda {
	private List<ItemVenda> itensVenda;
	private Funcionario funcionario;
	private FormaDePagamento formaDePagamento;
	
	public Venda(Funcionario funcionario, List<ItemVenda> itensVenda, FormaDePagamento formaDePagamento) {
		super();
		this.itensVenda = itensVenda;
		this.funcionario = funcionario;
		this.formaDePagamento = formaDePagamento;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	
	public double getValorTotal(){
		double valorTotal = 0;
		for(ItemVenda itemVenda : itensVenda){
			valorTotal += itemVenda.getValorTotal();
		}
		
		return valorTotal;
	}
}
