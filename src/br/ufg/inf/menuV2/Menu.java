package br.ufg.inf.menuV2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.ufg.inf.db.Sessao;
import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.pessoa.Gerente;

public class Menu {

	private List<IOpcaoMenu> opcoesMenu;

	public Menu(IOpcaoMenu... opcoesMenu) {
		this.opcoesMenu = Arrays.asList(opcoesMenu);
	}

	public void exibaOpcoes() {
		System.out.println(MensagensSistemaDeVendas.SELECIONE_UMA_FUNCAO);
		for (int i = 0; i < opcoesMenu.size(); i++) {
			if (menuAutorizado(opcoesMenu.get(i))) {

				System.out.printf("%d - %s\n", i, opcoesMenu.get(i).getNome());
			}

		}

		Scanner sc = new Scanner(System.in);
		String opcaoSelecionada = sc.nextLine();

		selecionarOpcao(Integer.parseInt(opcaoSelecionada));
	}

	private void selecionarOpcao(int opcao) {
		while (opcao < 0 || opcao > opcoesMenu.size() - 1 || !menuAutorizado(opcoesMenu.get(opcao))) {
			exibaOpcoes();
		}

		boolean exibirMenuNovamente = opcoesMenu.get(opcao).getAcao().get();
		if (exibirMenuNovamente) {
			exibaOpcoes();
		}
	}
	
	private boolean menuAutorizado(IOpcaoMenu opcaoMenu){
		return Sessao.FuncionarioLogado == null || Sessao.FuncionarioLogado instanceof Gerente ||(!(Sessao.FuncionarioLogado instanceof Gerente)
				&& opcaoMenu.autorizadoParaFuncionario());
	}
}
