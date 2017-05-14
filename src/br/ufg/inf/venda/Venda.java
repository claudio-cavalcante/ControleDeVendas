package br.ufg.inf.venda;

import java.util.List;

import br.ufg.inf.pagamento.IFormaDePagamento;
import br.ufg.inf.pessoa.Funcionario;

public class Venda {
	private List<ItemVenda> itensVenda;
	private Funcionario funcionario;
	private IFormaDePagamento formaDePagamento;
	
	public Venda(List<ItemVenda> itensVenda, IFormaDePagamento formaDePagamento) {
		super();
		this.itensVenda = itensVenda;
		this.formaDePagamento = formaDePagamento;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public IFormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public double getValorTotal(){
		double valorTotal = 0;
		for(ItemVenda itemVenda : itensVenda){
			valorTotal += itemVenda.getValorTotal();
		}
		
		return valorTotal;
	}
	
	
}
