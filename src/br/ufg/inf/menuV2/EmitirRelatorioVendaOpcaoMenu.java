package br.ufg.inf.menuV2;
import java.util.function.Supplier;

import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.relatorio.Relatorio;
import br.ufg.inf.relatorio.RelatorioDeVendas;

public class EmitirRelatorioVendaOpcaoMenu implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistemaDeVendas.EMITIR_RELATORIO_VENDA;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		Relatorio relatorio = new RelatorioDeVendas();
		System.out.println(relatorio.emitir());
		return () -> true;
	}
	
	@Override
	public boolean autorizadoParaFuncionario() {
		return false;
	}
}
