package br.ufg.inf.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.ufg.inf.pagamento.FormaDePagamentoEmCartao;
import br.ufg.inf.pagamento.FormaDePagamentoEmDinheiro;
import br.ufg.inf.pagamento.IFormaDePagamento;
import br.ufg.inf.pagamento.IProcessamentoDoPagamento;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;
import br.ufg.inf.venda.Caixa;
import br.ufg.inf.venda.ItemVenda;
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
				System.out.printf("%s: ", MensagensSistemaDeVendas.QUANTIDADE_INVALIDA);
			}
		} while (!qtdValida);

		Produto produto = new Produto(Integer.parseInt(codigo), descricao, Float.parseFloat(preco));
		Estoque.Instancia().adicionar(funcionarioLogado, produto, Integer.parseInt(qtd));
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
		} while (produtoBuscado == null);

		System.out.printf("%s: %s.\n", MensagensSistemaDeVendas.DESCRICAO, produtoBuscado.getDescricao());
		System.out.printf("%s: R$%.2f.\n", MensagensSistemaDeVendas.VALOR_PRODUTO, produtoBuscado.getPreco());
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
			realizarVenda();
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
			System.out.printf("%d - %s\n", 0, MensagensSistemaDeVendas.SAIR);
			System.out.printf("%d - %s\n", 1, MensagensSistemaDeVendas.LOGIN);
			System.out.printf("%d - %s\n", 2, MensagensSistemaDeVendas.CONSULTAR_PRECO);
			opcaoSelecionada = sc.nextLine();
		} while (!opcaoSelecionada.equals("0") && !opcaoSelecionada.equals("1") && !opcaoSelecionada.equals("2"));

		if (opcaoSelecionada.equals("0")) {
			System.exit(0);
		} else if (opcaoSelecionada.equals("1")) {
			login();
			exibirMenuPrincipal();
		} else if (opcaoSelecionada.equals("2")) {
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

	private void realizarVenda() {
		Scanner sc = new Scanner(System.in);
		List<ItemVenda> itens = new ArrayList<ItemVenda>();
		System.out.println(MensagensSistemaDeVendas.INICIANDO_VENDA);
		boolean continuarVenda = true;
		float valorTotal;
		do {
			System.out.println(MensagensSistemaDeVendas.PEDIDO_VENDA);
			System.out.println("----------------------------------");
			valorTotal = 0;
			for (ItemVenda item : itens) {
				System.out.printf(
						"Código: %d - Descrição: %s - Preço unitário: R$%.2f - Quantidade: %.2f - Preço total: R$%.2f.\n",
						item.getProduto().getCodigo(), item.getProduto().getDescricao(), item.getProduto().getPreco(),
						item.getQuantidade(), item.getValorTotal());
				valorTotal += item.getValorTotal();
			}
			System.out.println("-----------------------------------");

			String opcao = "";
			System.out.printf("0 - %s\n", MensagensSistemaDeVendas.FINALIZAR_VENDA);
			System.out.printf("1 - %s\n", MensagensSistemaDeVendas.ADICIONAR_PRODUTO);
			opcao = sc.nextLine();
			if (opcao.equals("0")) {
				if (itens.size() == 0) {
					System.out.println(MensagensSistemaDeVendas.NENHUM_PRODUTO);
				} else {
					continuarVenda = false;
				}
			} else if (opcao.equals("1")) {
				boolean codigoValido;
				String codigo;
				Produto produto = null;
				do {
					System.out.printf("%s: ", MensagensSistemaDeVendas.CODIGO);
					codigo = sc.next();
					codigoValido = StringUtils.isNumeric(codigo);
					if (!codigoValido) {
						System.out.printf("%s: ", MensagensSistemaDeVendas.CODIGO_INVALIDO);
					} else {
						produto = Estoque.Instancia().obtenhaProduto(Integer.parseInt(codigo));
						if (produto == null) {
							System.out.println(MensagensSistemaDeVendas.PRODUTO_NAO_ENCONTRADO);
							codigoValido = false;
						} else {
							for (ItemVenda item : itens) {
								if (item.getProduto().getCodigo() == Integer.parseInt(codigo)) {
									System.out.println(MensagensSistemaDeVendas.PRODUTO_JA_ADICIONADO);
									codigoValido = false;
								}
							}
						}
					}
				} while (!codigoValido);

				System.out.printf("%s: ", MensagensSistemaDeVendas.QUANTIDADE);
				boolean quantidadeValida;
				String quantidade;
				do {
					quantidade = sc.next();
					quantidadeValida = NumberUtils.isParsable(quantidade);
					if (!quantidadeValida) {
						System.out.printf("%s: ", MensagensSistemaDeVendas.QUANTIDADE_INVALIDA);
					}
				} while (!quantidadeValida);

				if (produto != null) {
					ItemVenda itemDeVenda = new ItemVenda(produto, Float.parseFloat(quantidade));
					itens.add(itemDeVenda);
				}
			}
		} while (continuarVenda);
		System.out.printf("%s: R$%.2f.\n\n", MensagensSistemaDeVendas.VALOR_TOTAL_VENDA, valorTotal);

		String opcaoPagamento;
		do {
			System.out.println(MensagensSistemaDeVendas.FORMA_PAGAMENTO);
			System.out.printf("0 - %s\n", MensagensSistemaDeVendas.DINHEIRO);
			System.out.printf("1 - %s\n", MensagensSistemaDeVendas.CARTAO);
			opcaoPagamento = sc.nextLine();
		} while (!opcaoPagamento.equals("0") && !opcaoPagamento.equals("1"));

		IFormaDePagamento formaDePagamento = null;
		String valorPago = "0";
		if (opcaoPagamento.equals("0")) {
			formaDePagamento = new FormaDePagamentoEmDinheiro();
			do {
				System.out.printf("%s: ", MensagensSistemaDeVendas.INFORME_VALOR_PAGO);
				valorPago = sc.next();
			} while (!NumberUtils.isParsable(valorPago));
		} else if (opcaoPagamento.equals("1")) {
			formaDePagamento = new FormaDePagamentoEmCartao();
			valorPago = Double.toString(valorTotal);
		}

		Venda venda = new Venda(itens, formaDePagamento);
		IProcessamentoDoPagamento processamento = venda.realizarPagamento(Double.parseDouble(valorPago));
		if (processamento.pagamentoRealizadoComSucesso()) {
			if (processamento.houveTroco()) {
				System.out.printf("%s: R$%.2f.\n\n", MensagensSistemaDeVendas.TROCO, processamento.valorDoTroco());
			}
		} else {
			System.out.println(processamento.mensagem());
		}

		System.out.printf("%s\n\n", MensagensSistemaDeVendas.VENDA_FINALIZADA);
	}

	private Caixa selecionarCaixa(String identificador) {
		return null;
	}

	private IFormaDePagamento selecionarMetodoDePagamento() {
		return null;
	}
}
