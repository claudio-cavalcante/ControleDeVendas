package br.ufg.inf.pagamento;

public interface IFormaDePagamento {
	
	public IProcessamentoDoPagamento realizarPagamento(double valorDaVenda, double valorPago);
}
