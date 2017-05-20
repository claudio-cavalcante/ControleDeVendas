package br.ufg.inf.pagamento;

public interface IProcessamentoDoPagamento {
	public boolean pagamentoRealizadoComSucesso();
	
	public Double valorDoTroco();
	
	public String mensagem();
}
