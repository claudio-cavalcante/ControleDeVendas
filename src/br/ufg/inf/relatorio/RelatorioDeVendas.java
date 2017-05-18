package br.ufg.inf.relatorio;

import java.util.Arrays;
import java.util.List;

import br.ufg.inf.db.DbContext;
import br.ufg.inf.venda.Caixa;

public class RelatorioDeVendas implements Relatorio {

	@Override
	public String emitir() {
		String relatorio = "";
		for (Caixa caixa : DbContext.Caixas) {
			relatorio += String.format("Caixa: %s\nValor total: %s\nVendedor: %s\n", caixa.getIdentificador(),
					caixa.getValorTotal(), caixa.getFuncionario().getNome());			
		}
		
		return relatorio;
	}
}
