package br.ufg.inf.menuV2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.ufg.inf.db.DbContext;
import br.ufg.inf.db.Sessao;
import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.pagamento.FormaDePagamentoEmCartao;
import br.ufg.inf.pagamento.FormaDePagamentoEmDinheiro;
import br.ufg.inf.pagamento.IFormaDePagamento;
import br.ufg.inf.pagamento.IProcessamentoDoPagamento;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;
import br.ufg.inf.relatorio.Relatorio;
import br.ufg.inf.relatorio.RelatorioDeVendas;
import br.ufg.inf.venda.Caixa;
import br.ufg.inf.venda.ItemVenda;
import br.ufg.inf.venda.Venda;

public class OpcaoMenuRealizarVenda implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistemaDeVendas.REALIZAR_VENDA;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		selecionarCaixa();
		realizarVenda();
		return () -> true;
	}
	
	@Override
	public EnumPapel[] papeisAutorizados() {
		return new EnumPapel[]{ EnumPapel.GERENTE, EnumPapel.FUNCIONARIO };
	}
	
	private void selecionarCaixa() {
		
		Funcionario funcionario = Sessao.FuncionarioLogado;
		
		if (DbContext.Caixas.size() == 0) {
			Caixa primeiroCaixa = new Caixa("1", funcionario);
			DbContext.Caixas.add(primeiroCaixa);
			Sessao.caixaSelecionado = primeiroCaixa;
		} else {
			String opcao;
			Scanner sc = new Scanner(System.in);
			do {
				System.out.println(MensagensSistemaDeVendas.SELECIONE_UMA_FUNCAO);
				System.out.printf("0 - %s\n", MensagensSistemaDeVendas.ESCOLHER_CAIXA);
				System.out.printf("1 - %s\n", MensagensSistemaDeVendas.INICIAR_NOVO_CAIXA);
				opcao = sc.nextLine();
			} while (!opcao.equals("0") && !opcao.equals("1"));

			if (opcao.equals("0")) {
				String caixaSelecionado1;
				boolean caixaValido = false;
				do {
					System.out.println(MensagensSistemaDeVendas.SELECIONE_CAIXA);
					DbContext.Caixas.forEach(x -> System.out.printf("Caixa: %s\n", x.getIdentificador()));
					caixaSelecionado1 = sc.nextLine();
					for (Caixa caixa : DbContext.Caixas) {
						if (caixa.getIdentificador().equals(caixaSelecionado1)) {
							caixaValido = true;
						}
					}
				} while (!caixaValido);
			} else if (opcao.equals("1")) {
				Caixa novoCaixa = new Caixa(Integer.toString(DbContext.Caixas.size()), funcionario);
				DbContext.Caixas.add(novoCaixa);
				Sessao.caixaSelecionado = novoCaixa;
			}
		}
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

		boolean pagamentoRealizadoComSucesso = false;
		do {
			String opcaoPagamento = "";
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
			pagamentoRealizadoComSucesso = processamento.pagamentoRealizadoComSucesso();
			if (pagamentoRealizadoComSucesso) {
				if (processamento.houveTroco()) {
					System.out.printf("%s: R$%.2f.\n\n", MensagensSistemaDeVendas.TROCO, processamento.valorDoTroco());
				}

				Sessao.caixaSelecionado.registrarVenda(venda);
			} else {
				System.out.println(processamento.mensagem());
			}
		} while (!pagamentoRealizadoComSucesso);
		System.out.printf("%s\n\n", MensagensSistemaDeVendas.VENDA_FINALIZADA);
	}


}
