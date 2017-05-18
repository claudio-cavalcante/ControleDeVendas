package br.ufg.inf.menuV2;
import java.util.function.Supplier;

import br.ufg.inf.menu.MensagensSistemaDeVendas;

public class OpcaoMenuSair implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistemaDeVendas.SAIR;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		return () -> {
			System.out.println(MensagensSistemaDeVendas.OBRIGADO_POR_UTILIZAR);
			System.exit(0);
			return true;
		};
	}
	
	@Override
	public boolean autorizadoParaFuncionario() {
		return true;
	}

}
