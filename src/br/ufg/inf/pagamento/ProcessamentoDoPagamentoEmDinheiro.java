package br.ufg.inf.pagamento;

public class ProcessamentoDoPagamentoEmDinheiro implements IProcessamentoDoPagamento{
	
	private double valorDeVenda;
	private double valorPago;
	
	public ProcessamentoDoPagamentoEmDinheiro(double valorDeVenda, double valorPago){
		this.valorDeVenda = valorDeVenda;
		this.valorPago = valorPago;
	}
	
	@Override
	public boolean pagamentoRealizadoComSucesso() {		
		return !houveTroco() && valorDoTroco() < 0 ? false : true;
	}	

	@Override
	public Double valorDoTroco() {
		return valorPago - valorDeVenda;
	}

	@Override
	public String mensagem() {		
		return pagamentoRealizadoComSucesso() ? "" : "Valor insuficiente!";
	}
	
	private boolean houveTroco() {
		return valorPago > valorDeVenda;
	}
}
