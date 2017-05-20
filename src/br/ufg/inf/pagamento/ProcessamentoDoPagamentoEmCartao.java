package br.ufg.inf.pagamento;

import java.util.Random;

public class ProcessamentoDoPagamentoEmCartao implements IProcessamentoDoPagamento {

	private Boolean pagamentoRealizadoComSucesso;
	private double valorPago;
	
	public ProcessamentoDoPagamentoEmCartao(double valorPago){
		this.valorPago = valorPago;
	}
	
	@Override
	public boolean pagamentoRealizadoComSucesso() {			
		if(pagamentoRealizadoComSucesso == null ){
			pagamentoRealizadoComSucesso = new Random().nextBoolean() || this.valorPago < 100;
		}
		
		return pagamentoRealizadoComSucesso;
	}

	@Override
	public Double valorDoTroco() {
		return null;
	}

	@Override
	public String mensagem() {
		return pagamentoRealizadoComSucesso() ? "TRANSAÇÃO EFETUADA!" : "TRANSAÇÃO NÃO AUTORIZADA";
	}

}
