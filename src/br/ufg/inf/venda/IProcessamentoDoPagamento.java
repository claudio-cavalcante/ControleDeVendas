package br.ufg.inf.venda;

public interface IProcessamentoDoPagamento {
	public boolean pagamentoRealizadoComSucesso();
	
	public boolean houveTroco();
	
	public double valorDoTroco();
	
	public String mensagem();
}
