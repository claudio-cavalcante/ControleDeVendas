package br.ufg.inf.menuV2;
import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;

public class ConsultarPrecoOpcaoMenu implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistemaDeVendas.CONSULTAR_PRECO;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		return () -> {
			consultarPreco();
			return true;
		};
	}
	
	@Override
	public boolean autorizadoParaFuncionario() {
		return true;
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
}
