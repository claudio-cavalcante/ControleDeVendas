package br.ufg.inf.menu;

import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.Sessao;
import br.ufg.inf.db.Repositorio;
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

			Menu menuPrincipal = new Menu(
					new OpcaoMenuSair(),
					new OpcaoMenuAdicionarProdutoEstoque(),
					new OpcaoMenuRealizarVenda(),
					new OpcaoMenuConsultarPreco(),
					new OpcaoMenuEmitirRelatorioVenda(),
					new OpcaoMenuEmitirRelatorioEstoque(),
					new OpcaoMenuLogoff());
			
			menuPrincipal.exibaOpcoes();

			return true;
		};
	}
	
	@Override
	public EnumPapel[] papeisAutorizados() {
		return new EnumPapel[]{ EnumPapel.GERENTE, EnumPapel.FUNCIONARIO };
	}

	private void login() {
		Scanner sc = new Scanner(System.in);
		boolean loginValido = false;
		
		String usuarioInformado;
		System.out.printf(MensagensSistema.USUARIO + ": ");		
		usuarioInformado = sc.next();
		
		System.out.printf(MensagensSistema.SENHA + ": ");		
		String senha = sc.next();
		
		loginValido = loginValido(usuarioInformado, senha);
		
		if (!loginValido) {
			System.out.println(MensagensSistema.DADOS_LOGIN_INVALIDOS);
			System.out.println();
			return;
		}
		
		Sessao.setFuncionarioLogado(Repositorio.getInstancia().funcionarios().get(Integer.parseInt(usuarioInformado)));	
	}	

	private boolean loginValido(String usuario, String senha) {
		if (!ehValorNumerico(usuario)) {
			return false; 
		} else {
			return Repositorio.getInstancia().senhaValida(Integer.parseInt(usuario), senha);			
		}
	}

	private boolean ehValorNumerico(String valor) {
		return !valor.trim().isEmpty() && StringUtils.isNumeric(valor.trim());
	}

}
