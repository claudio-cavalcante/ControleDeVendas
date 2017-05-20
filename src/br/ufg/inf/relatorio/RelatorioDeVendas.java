package br.ufg.inf.relatorio;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import br.ufg.inf.Sessao;
import br.ufg.inf.db.Repositorio;
import br.ufg.inf.menu.MensagensSistema;
import br.ufg.inf.venda.Caixa;
import br.ufg.inf.venda.Venda;

public class RelatorioDeVendas implements Relatorio {

	@Override
	public String emitir() {
		String relatorio = MensagensSistema.SEPARADOR + "\n";
		relatorio += MensagensSistema.RELATORIO_VENDAS + "\n";
		for (Entry<Integer, Caixa> entrySetCaixa : Repositorio.getInstancia().caixas().entrySet()) {
			Caixa caixa = entrySetCaixa.getValue();
			if (caixa.getVendas() == null
					|| caixa.getVendas().size() == 0) {
				break;
			}
			
			relatorio += "\nCaixa: " + caixa.getIdentificador() + ".\n";
			for (Venda venda : caixa.getVendas()) {
				relatorio += String.format("Funcionário: %s\nValor total: R$%.2f\n\n", venda.getFuncionario().getNome(),
						venda.getValorTotal());
			}
		}
		
		relatorio += MensagensSistema.SEPARADOR + "\n";
		return relatorio;
	}
}
