package br.ufg.inf.menu;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.pagamento.IFormaDePagamento;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.produto.Produto;
import br.ufg.inf.venda.Caixa;
import br.ufg.inf.venda.Venda;

public class MenuPrincipal {
	public void execute() {
		System.out.println(MensagensSistemaDeVendas.BEM_VINDO);
		Scanner sc = new Scanner(System.in);

		int usuario = 0;
		String senha = "";
		boolean loginValido = false;
		do {
			System.out.printf(MensagensSistemaDeVendas.USUARIO + ": ");
			String usuarioInformado = sc.next();
			System.out.printf(MensagensSistemaDeVendas.SENHA + ": ");
			senha = sc.next();

			if (usuarioInformado.trim().isEmpty() || !StringUtils.isNumeric(usuarioInformado)) {
				System.out.println(MensagensSistemaDeVendas.DADOS_LOGIN_INVALIDOS);
			} else {
				usuario = Integer.parseInt(usuarioInformado);
				loginValido = login(usuario, senha);
				if (!loginValido) {
					System.out.println(MensagensSistemaDeVendas.DADOS_LOGIN_INVALIDOS);
				}
			}
		} while (!loginValido);

		String opcaoSelecionada;
		do {
			System.out.println(MensagensSistemaDeVendas.SELECIONE_UMA_FUNCAO);
			System.out.printf("%d - %s\n", EnumFuncoesDoSistema.SAIR.ordinal(), MensagensSistemaDeVendas.SAIR);
			opcaoSelecionada = sc.next();
		} while (!EnumFuncoesDoSistema.funcaoEhValida(opcaoSelecionada));
	}

	private boolean login(int usuario, String senha) {
		return usuario == 1 && senha.equals("123");
	}

	private Caixa selecionarCaixa(String identificador) {
		return new Caixa(identificador, new Funcionario("Teste."));
	}

	private Venda adicionarProdutosNaVenda(Venda venda, List<Produto> produtos) {
		return null;
	}

	private IFormaDePagamento selecionarMetodoDePagamento() {
		return null;
	}

	private boolean finalizarVenda(Venda venda, IFormaDePagamento formaDePagamento) {
		return true;
	}
}
