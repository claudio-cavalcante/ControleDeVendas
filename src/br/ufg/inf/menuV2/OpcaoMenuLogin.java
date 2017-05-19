package br.ufg.inf.menuV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

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

			if (Sessao.funcionarioLogado == null) {
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
	public boolean autorizadoParaFuncionario() {
		return true;
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

		for (Funcionario funcionario : getFuncionarios()) {
			if (funcionario.getMatricula() == Integer.parseInt(usuarioInformado)) {
				Sessao.funcionarioLogado = funcionario;
			}
		}
	}

	private List<Funcionario> getFuncionarios() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Gerente(1, "Gerente"));
		funcionarios.add(new Funcionario(2, "Cláudio"));
		funcionarios.add(new Funcionario(3, "Danillo"));
		funcionarios.add(new Funcionario(4, "Vinícius"));

		return funcionarios;
	}

	private boolean loginValido(String usuario, String senha) {
		if (!ehValorNumerico(usuario)) {
			return false;
		} else {
			return Integer.parseInt(usuario) == 1 && senha.equals("123")
					|| Integer.parseInt(usuario) == 2 && senha.equals("234")
					|| Integer.parseInt(usuario) == 3 && senha.equals("345")
					|| Integer.parseInt(usuario) == 4 && senha.equals("456");
		}
	}

	private boolean ehValorNumerico(String valor) {
		return !valor.trim().isEmpty() && StringUtils.isNumeric(valor.trim());
	}

}
