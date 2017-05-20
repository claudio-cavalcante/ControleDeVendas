package br.ufg.inf;

import br.ufg.inf.menu.MensagensSistema;
import br.ufg.inf.menu.Menu;
import br.ufg.inf.menu.OpcaoMenuConsultarPreco;
import br.ufg.inf.menu.OpcaoMenuLogin;
import br.ufg.inf.menu.OpcaoMenuSair;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;

public class TelaInicio {

	public static void main(String[] args) {
		populeEstoque();
		System.out.println(MensagensSistema.BEM_VINDO);
		Menu telaInicial = new Menu(new OpcaoMenuSair(), new OpcaoMenuLogin(), new OpcaoMenuConsultarPreco());
		telaInicial.exibaOpcoes();
	}

	private static void populeEstoque() {
		Estoque.Instancia().adicionar(new Produto(1, "Leite", 5), 10);
		Estoque.Instancia().adicionar(new Produto(2, "Ovos", 12), 10);
		Estoque.Instancia().adicionar(new Produto(2, "Farinha", 2), 100);
	}
}
