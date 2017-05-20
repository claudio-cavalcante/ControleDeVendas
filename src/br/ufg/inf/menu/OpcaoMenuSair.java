package br.ufg.inf.menu;
import java.util.function.Supplier;

import br.ufg.inf.menu.MensagensSistema;

public class OpcaoMenuSair implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistema.SAIR;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		return () -> {
			System.out.println(MensagensSistema.OBRIGADO_POR_UTILIZAR);
			System.exit(0);
			return true;
		};
	}
	
	@Override
	public EnumPapel[] papeisAutorizados() {
		return new EnumPapel[]{ EnumPapel.GERENTE, EnumPapel.FUNCIONARIO };
	}
}
