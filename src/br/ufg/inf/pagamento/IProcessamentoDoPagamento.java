package br.ufg.inf.pagamento;

public interface IProcessamentoDoPagamento {
	public boolean pagamentoRealizadoComSucesso();
	
	public boolean houveTroco();
	
	public double valorDoTroco();
	
	public String mensagem();
}
