package br.ufg.inf.pagamento;

public class FormaDePagamentoEmCartao implements IFormaDePagamento{

	@Override
	public IProcessamentoDoPagamento realizarPagamento(double valorDaVenda, double valorPago) {		
		return new ProcessamentoDoPagamentoEmCartao(valorPago);
	}

}
