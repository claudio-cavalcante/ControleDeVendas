package br.ufg.inf.pagamento;

public class FormaDePagamentoEmDinheiro implements IFormaDePagamento{

	@Override
	public IProcessamentoDoPagamento realizarPagamento(double valorDaVenda, double valorPago) {
		return new ProcessamentoDoPagamentoEmDinheiro(valorDaVenda, valorPago);
	}

}
