package br.ufg.inf;

import br.ufg.inf.menu.Menu;
import br.ufg.inf.menu.OpcaoMenuConsultarPreco;
import br.ufg.inf.menu.OpcaoMenuLogin;
import br.ufg.inf.menu.OpcaoMenuSair;

public class TelaInicio {

	public static void main(String[] args) {
		Menu telaInicial = new Menu(new OpcaoMenuSair(), new OpcaoMenuLogin(), new OpcaoMenuConsultarPreco());
		telaInicial.exibaOpcoes();
	}

}
