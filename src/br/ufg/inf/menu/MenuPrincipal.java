package br.ufg.inf.menu;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.pagamento.IFormaDePagamento;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;
import br.ufg.inf.venda.Caixa;
import br.ufg.inf.venda.Venda;

public class MenuPrincipal {
	private List<Funcionario> funcionarios;
	
	public void execute() {
		login();
		funcionarios.add(new Gerente("Gerente"));
		EnumFuncoesDoSistema funcao = obtenhaFuncaoDesejada();
		switch (funcao) {
		case SAIR:
			System.out.println(MensagensSistemaDeVendas.OBRIGADO_POR_UTILIZAR);
			break;
		case ADICIONAR_PRODUTO_ESTOQUE:
			adicionarProdutoNoEstoque();
			break;
		case REALIZAR_VENDA:
			break;
		case CONSULTAR_PRECO:
			break;
		case EMITIR_RELATORIO_VENDA:
			break;
		case EMITIR_RELATORIO_ESTOQUE:
			break;
		}
	}

	private void adicionarProdutoNoEstoque() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Favor informar os dados do produto:");
		String codigo = sc.next();
		
		
		estoque.adicionar(funcionarios.get(0), produto, qtd);
	}
	
	private EnumFuncoesDoSistema obtenhaFuncaoDesejada() {
		String opcaoSelecionada = "";
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(MensagensSistemaDeVendas.SELECIONE_UMA_FUNCAO);
			System.out.printf("%d - %s\n", EnumFuncoesDoSistema.SAIR.ordinal(), MensagensSistemaDeVendas.SAIR);
			System.out.printf("%d - %s\n", EnumFuncoesDoSistema.ADICIONAR_PRODUTO_ESTOQUE.ordinal(),
					MensagensSistemaDeVendas.ADICIONAR_PRODUTO_ESTOQUE);
			System.out.printf("%d - %s\n", EnumFuncoesDoSistema.REALIZAR_VENDA.ordinal(),
					MensagensSistemaDeVendas.REALIZAR_VENDA);
			System.out.printf("%d - %s\n", EnumFuncoesDoSistema.CONSULTAR_PRECO.ordinal(),
					MensagensSistemaDeVendas.CONSULTAR_PRECO);
			System.out.printf("%d - %s\n", EnumFuncoesDoSistema.EMITIR_RELATORIO_VENDA.ordinal(),
					MensagensSistemaDeVendas.EMITIR_RELATORIO_VENDA);
			System.out.printf("%d - %s\n", EnumFuncoesDoSistema.EMITIR_RELATORIO_ESTOQUE.ordinal(),
					MensagensSistemaDeVendas.EMITIR_RELATORIO_ESTOQUE);
			opcaoSelecionada = sc.next();
		} while (!EnumFuncoesDoSistema.funcaoEhValida(opcaoSelecionada));

		return EnumFuncoesDoSistema.getFuncao(Integer.parseInt(opcaoSelecionada));
	}

	private Funcionario login() {
		System.out.println(MensagensSistemaDeVendas.BEM_VINDO);
		Scanner sc = new Scanner(System.in);
		boolean loginValido = false;
		do {
			System.out.printf(MensagensSistemaDeVendas.USUARIO + ": ");
			String usuarioInformado = sc.next();
			System.out.printf(MensagensSistemaDeVendas.SENHA + ": ");
			String senha = sc.next();
			loginValido = loginValido(usuarioInformado, senha);
			if (!loginValido) {
				System.out.println(MensagensSistemaDeVendas.DADOS_LOGIN_INVALIDOS);
			}
		} while (!loginValido);
		
		
	}

	private boolean loginValido(String usuario, String senha) {
		if (usuario.trim().isEmpty() || !StringUtils.isNumeric(usuario)) {
			return false;
		} else {
			return Integer.parseInt(usuario) == 1 && senha.equals("123");
		}
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
