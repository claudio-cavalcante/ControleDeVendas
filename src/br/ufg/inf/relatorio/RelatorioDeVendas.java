package br.ufg.inf.relatorio;

import java.util.Arrays;
import java.util.List;

import br.ufg.inf.db.DbContext;
import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.venda.Caixa;

public class RelatorioDeVendas implements Relatorio {

	@Override
	public String emitir() {
		if (DbContext.Caixas == null
				|| DbContext.Caixas.size() == 0) {
			return MensagensSistemaDeVendas.NENHUM_CAIXA_UTILIZADO;
		}
		
		String relatorio = "";
		for (Caixa caixa : DbContext.Caixas) {
			relatorio += String.format("Caixa: %s\nValor total: R$%.2f\nVendedor: %s\n", caixa.getIdentificador(),
					caixa.getValorTotal(), caixa.getFuncionario().getNome());			
		}
		
		return relatorio;
	}
}
