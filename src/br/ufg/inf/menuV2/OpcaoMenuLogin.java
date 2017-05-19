package br.ufg.inf.menuV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.db.DbContext;
import br.ufg.inf.db.Sessao;
import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;

public class OpcaoMenuLogin implements IOpcaoMenu {

	@Override
	public String getNome() {
		return "Login";
	}

	@Override
	public Supplier<Boolean> getAcao() {
		return () -> {
			login();

			if (Sessao.FuncionarioLogado == null) {
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

			return false;
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
		System.out.printf(MensagensSistemaDeVendas.USUARIO + ": ");		
		usuarioInformado = sc.next();
		
		System.out.printf(MensagensSistemaDeVendas.SENHA + ": ");		
		String senha = sc.next();
		
		loginValido = loginValido(usuarioInformado, senha);
		
		if (!loginValido) {
			System.out.println(MensagensSistemaDeVendas.DADOS_LOGIN_INVALIDOS);
			return;
		}
		
		Sessao.FuncionarioLogado = DbContext.funcionarios().get(Integer.parseInt(usuarioInformado));	
	}	

	private boolean loginValido(String usuario, String senha) {
		if (!ehValorNumerico(usuario)) {
			return false; 
		} else {
			return DbContext.senhaValida(Integer.parseInt(usuario), senha);			
		}
	}

	private boolean ehValorNumerico(String valor) {
		return !valor.trim().isEmpty() && StringUtils.isNumeric(valor.trim());
	}

}
