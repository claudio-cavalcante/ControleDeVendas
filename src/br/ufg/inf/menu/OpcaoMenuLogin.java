package br.ufg.inf.menu;

import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.Sessao;
import br.ufg.inf.db.Repositorio;
import br.ufg.inf.exception.LoginException;
import br.ufg.inf.menu.MensagensSistema;

public class OpcaoMenuLogin implements IOpcaoMenu {

	@Override
	public String getNome() {
		return "Login";
	}

	@Override
	public Supplier<Boolean> getAcao() {
		return () -> {
			login();

			if (Sessao.getFuncionarioLogado() == null) {
				return true;
			}

			Menu menuPrincipal = new Menu(new OpcaoMenuSair(), new OpcaoMenuManterEstoque(),
					new OpcaoMenuRealizarVenda(), new OpcaoMenuConsultarPreco(), new OpcaoMenuEmitirRelatorioVenda(),
					new OpcaoMenuEmitirRelatorioEstoque(), new OpcaoMenuLogoff());

			menuPrincipal.exibaOpcoes();

			return true;
		};
	}

	@Override
	public EnumPapel[] papeisAutorizados() {
		return new EnumPapel[] { EnumPapel.GERENTE, EnumPapel.FUNCIONARIO };
	}

	private void login() {
		Scanner sc = new Scanner(System.in);

		String usuarioInformado;
		System.out.printf("%s: ", MensagensSistema.USUARIO);
		usuarioInformado = sc.next();

		System.out.printf("%s: ", MensagensSistema.SENHA);
		String senha = sc.next();

		try {
			Repositorio.getInstancia().efetueLogin(usuarioInformado, senha);
		} catch (LoginException e) {
			System.out.println(e.getMessage());
			System.out.println();
			return;
		}

		Sessao.setFuncionarioLogado(Repositorio.getInstancia().funcionarios().get(Integer.parseInt(usuarioInformado)));
	}
}
