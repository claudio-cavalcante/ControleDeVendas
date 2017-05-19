package br.ufg.inf.relatorio;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import br.ufg.inf.db.DbContext;
import br.ufg.inf.db.Sessao;
import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.venda.Caixa;

public class RelatorioDeVendas implements Relatorio {

	@Override
	public String emitir() {
		if (Sessao.getCaixaSelecionado() == null) {
			return MensagensSistemaDeVendas.NENHUM_CAIXA_UTILIZADO;
		}
		
		String relatorio = "";
		for (Entry<Integer, Caixa> entrySetCaixa : DbContext.caixas().entrySet()) {
			Caixa caixa = entrySetCaixa.getValue();
			
			relatorio += String.format("Caixa: %s\nValor total: R$%.2f\nVendedor: %s\n", caixa.getIdentificador(),
					caixa.getValorTotal(), caixa.getFuncionario().getNome());			
		}
		
		return relatorio;
	}
}
