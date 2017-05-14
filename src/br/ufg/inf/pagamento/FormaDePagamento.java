package br.ufg.inf.pagamento;

public interface FormaDePagamento {
	
	public IProcessamentoDoPagamento realizarPagamento(double valorDaVenda, double valorPago);
}
