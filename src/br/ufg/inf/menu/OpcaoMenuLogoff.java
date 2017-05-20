package br.ufg.inf.menu;
import java.util.function.Supplier;

import br.ufg.inf.menu.MensagensSistemaDeVendas;

public class OpcaoMenuLogoff implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistemaDeVendas.LOGOFF;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		return () -> false;
	}
	
	@Override
	public EnumPapel[] papeisAutorizados() {
		return new EnumPapel[]{ EnumPapel.GERENTE, EnumPapel.FUNCIONARIO };
	}

}
