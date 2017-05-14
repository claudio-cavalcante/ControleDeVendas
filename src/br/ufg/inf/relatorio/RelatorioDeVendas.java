package br.ufg.inf.relatorio;

import java.util.Arrays;
import java.util.List;

import br.ufg.inf.venda.Caixa;

public class RelatorioDeVendas implements Relatorio{
	private List<Caixa> caixas;
	
	public RelatorioDeVendas(Caixa... caixas){
		this.caixas = Arrays.asList(caixas);
	}

	@Override
	public String emitir() {
		// TODO Auto-generated method stub
		return null;
	}
}
