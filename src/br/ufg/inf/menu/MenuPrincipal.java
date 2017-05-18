package br.ufg.inf.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	private Funcionario funcionarioLogado;

	public void execute() {
		populeDados();
		exibaTelaInicial();		
	}

	private Venda adicionarProdutosNaVenda(Venda venda, List<Produto> produtos) {
		return null;
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

	private void consultarPreco() {
		String codigoProduto;
		Produto produtoBuscado = null;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.printf("%s: ", MensagensSistemaDeVendas.INFORME_CODIGO_PRODUTO);
			codigoProduto = sc.next();
			if (codigoProduto.trim().equals("") || !ehValorNumerico(codigoProduto)) {
				System.out.println(MensagensSistemaDeVendas.CODIGO_INVALIDO);
			} else {
				produtoBuscado = Estoque.Instancia().obtenhaProduto(Integer.parseInt(codigoProduto));
				if (produtoBuscado == null) {
					System.out.println(MensagensSistemaDeVendas.PRODUTO_NAO_ENCONTRADO);
				}
			}
		} while(produtoBuscado == null);
		
		System.out.printf("%s: R$ %.2f.\n", MensagensSistemaDeVendas.VALOR_PRODUTO, produtoBuscado.getPreco());
	}
	
	private boolean ehValorNumerico(String valor) {
		return !valor.trim().isEmpty() && StringUtils.isNumeric(valor.trim());
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
			consultarPreco();
			break;
		case EMITIR_RELATORIO_VENDA:
			break;
		case EMITIR_RELATORIO_ESTOQUE:
			break;
		case LOGOFF:
			execute();
		}

		exibirMenuPrincipal();
	}

	private void exibaTelaInicial() {
		System.out.println(MensagensSistemaDeVendas.BEM_VINDO);
		String opcaoSelecionada = "";
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(MensagensSistemaDeVendas.SELECIONE_UMA_FUNCAO);
			System.out.printf("%d - %s\n", 0, MensagensSistemaDeVendas.LOGIN);
			System.out.printf("%d - %s\n", 1, MensagensSistemaDeVendas.CONSULTAR_PRECO);
			opcaoSelecionada = sc.nextLine();
		} while (!opcaoSelecionada.equals("0") && !opcaoSelecionada.equals("1"));
		
		if (opcaoSelecionada.equals("0")) {
			login();
			exibirMenuPrincipal();
		} else if (opcaoSelecionada.equals("1")) {
			consultarPreco();
			exibaTelaInicial();
		}		
	}
	
	private boolean finalizarVenda(Venda venda, IFormaDePagamento formaDePagamento) {
		return true;
	}
	
	private boolean funcionarioLogadoEhGerente() {
		return funcionarioLogado.getClass().getName() == Gerente.class.getName();
	}

	private EnumFuncoesDoSistema obtenhaFuncaoDesejada() {
		String opcaoSelecionada = "";
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(MensagensSistemaDeVendas.SELECIONE_UMA_FUNCAO);
			Map<EnumFuncoesDoSistema, String> funcoes = EnumFuncoesDoSistema.obtenhaFuncoes(funcionarioLogado);
			for (Map.Entry<EnumFuncoesDoSistema, String> entry : funcoes.entrySet()) {
				System.out.printf("%d - %s\n", entry.getKey().ordinal(), entry.getValue());
			}

			opcaoSelecionada = sc.next();
		} while (!EnumFuncoesDoSistema.funcaoEhValida(opcaoSelecionada));

		return EnumFuncoesDoSistema.getFuncao(Integer.parseInt(opcaoSelecionada));
	}

	private void login() {		
		Scanner sc = new Scanner(System.in);
		boolean loginValido = false;
		String usuarioInformado;
		do {
			System.out.printf(MensagensSistemaDeVendas.USUARIO + ": ");
			usuarioInformado = sc.next();
			System.out.printf(MensagensSistemaDeVendas.SENHA + ": ");
			String senha = sc.next();
			loginValido = loginValido(usuarioInformado, senha);
			if (!loginValido) {
				System.out.println(MensagensSistemaDeVendas.DADOS_LOGIN_INVALIDOS);
			}
		} while (!loginValido);

		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getMatricula() == Integer.parseInt(usuarioInformado)) {
				funcionarioLogado = funcionario;
			}
		}
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

	private void populeDados() {
		funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Gerente(1, "Gerente"));
		funcionarios.add(new Funcionario(2, "Cláudio"));
		funcionarios.add(new Funcionario(3, "Danillo"));
		funcionarios.add(new Funcionario(4, "Vinícius"));
		Estoque.Instancia().adicionar(funcionarios.get(0), new Produto(1, "Leite", 5), 10);
		Estoque.Instancia().adicionar(funcionarios.get(0), new Produto(2, "Ovos", 12), 10);
		Estoque.Instancia().adicionar(funcionarios.get(0), new Produto(2, "Farinha", 2), 100);
	}
	
	private Caixa selecionarCaixa(String identificador) {
		return null;
	}

	private IFormaDePagamento selecionarMetodoDePagamento() {
		return null;
	}
}
