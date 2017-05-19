package br.ufg.inf.venda;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.produto.Estoque;

public class Caixa {
	private List<Venda> vendas;
	private Funcionario funcionario;
	private String identificador;
	
	public Caixa(String identificador){
		this.identificador = identificador;
		this.vendas = new ArrayList<Venda>();
	}
	
	public Caixa(String identificador, Funcionario funcionario){
		this(identificador);
		this.funcionario = funcionario;
	}
	
	public void registrarVenda(Venda venda){
		venda.setFuncionario(this.funcionario);
		removerDoEstoque(venda.getItensVenda());
		
		vendas.add(venda);
	}	

	public List<Venda> getVendas() {
		return vendas;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario){
		this.funcionario = funcionario;
	}

	public String getIdentificador() {
		return identificador;
	}
	
	public double getValorTotal(){
		double valorTotal =0;
		for(Venda venda : this.vendas){
			valorTotal += venda.getValorTotal();
		}
		
		return valorTotal;
	}
	
	private void removerDoEstoque(List<ItemVenda> itensVenda) {
		for(ItemVenda itemVenda : itensVenda){
			Estoque.Instancia().remover(itemVenda.getProduto(), (int)itemVenda.getQuantidade());
		}		
	}
}
