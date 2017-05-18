package br.ufg.inf.menuV2;
import java.util.function.Supplier;

import br.ufg.inf.menu.MensagensSistemaDeVendas;

public class LogoffOpcaoMenu implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistemaDeVendas.LOGOFF;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		return () -> true;
	}
	
	@Override
	public boolean autorizadoParaFuncionario() {
		return true;
	}

}
