package br.ufg.inf.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

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
		funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Gerente("Gerente"));
		exibirMenuPrincipal();
	}

	private void exibirMenuPrincipal() {
		EnumFuncoesDoSistema funcao = obtenhaFuncaoDesejada();
		switch (funcao) {
		case SAIR:
			System.out.println(MensagensSistemaDeVendas.OBRIGADO_POR_UTILIZAR);
			System.exit(0);
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
		
		exibirMenuPrincipal();
	}
	
	private void adicionarProdutoNoEstoque() {
		Scanner sc = new Scanner(System.in);
		System.out.println(MensagensSistemaDeVendas.FAVOR_INFORMAR_DADOS_PRODUTO);
		System.out.printf("%s: ", MensagensSistemaDeVendas.CODIGO);
		boolean codigoValido = false;
		String codigo;
		do {
			codigo = sc.next();
			codigoValido = StringUtils.isNumeric(codigo);
			if (!codigoValido) {
				System.out.printf("%s: ", MensagensSistemaDeVendas.CODIGO_INVALIDO);
			}
		} while (!codigoValido);

		System.out.printf("%s: ", MensagensSistemaDeVendas.DESCRICAO);
		String descricao = sc.next();

		System.out.printf("%s: ", MensagensSistemaDeVendas.PRECO);
		boolean precoValido;
		String preco;
		do {
			preco = sc.next();
			precoValido = NumberUtils.isParsable(preco);
			if (!precoValido) {
				System.out.printf("%s: ", MensagensSistemaDeVendas.PRECO_INVALIDO);
			}
		} while (!precoValido);
		
		System.out.printf("%s: ", MensagensSistemaDeVendas.QUANTIDADE);
		boolean qtdValida;
		String qtd;
		do {
			qtd = sc.next();
			qtdValida = NumberUtils.isParsable(qtd);
			if (!qtdValida) {
				System.out.printf("%s: ", MensagensSistemaDeVendas.PRECO_INVALIDO);
			}
		} while (!qtdValida);
				
		Produto produto = new Produto(Integer.parseInt(codigo), descricao, Float.parseFloat(preco));
		Estoque.Instancia().adicionar(funcionarios.get(0), produto, Integer.parseInt(qtd));
		System.out.println(MensagensSistemaDeVendas.PRODUTO_ADICIONADO_ESTOQUE);
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

	private void login() {
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
