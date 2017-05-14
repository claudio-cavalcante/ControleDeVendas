package br.ufg.inf.venda;

public interface FormaDePagamento {
	
	public IProcessamentoDoPagamento realizarPagamento(double valorDaVenda, double valorPago);
}
