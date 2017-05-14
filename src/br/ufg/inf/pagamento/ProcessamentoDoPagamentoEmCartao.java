package br.ufg.inf.pagamento;

import java.util.Random;

public class ProcessamentoDoPagamentoEmCartao implements IProcessamentoDoPagamento {

	private Boolean pagamentoRealizadoComSucesso;
	
	@Override
	public boolean pagamentoRealizadoComSucesso() {			
		if(pagamentoRealizadoComSucesso == null ){
			pagamentoRealizadoComSucesso = new Random().nextBoolean();
		}
		
		return pagamentoRealizadoComSucesso;
	}

	@Override
	public boolean houveTroco() {
		return false;
	}

	@Override
	public double valorDoTroco() {
		return 0;
	}

	@Override
	public String mensagem() {
		return pagamentoRealizadoComSucesso() ? "TRANSAÇÃO EFETUADA!" : "TRANSAÇÃO NÃO AUTORIZADA";
	}

}
