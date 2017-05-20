package br.ufg.inf.menu;

import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.menu.MensagensSistema;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;

public class OpcaoMenuConsultarPreco implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistema.CONSULTAR_PRECO;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		return () -> {
			consultarPreco();
			return true;
		};
	}

	@Override
	public EnumPapel[] papeisAutorizados() {
		return null;
	}

	private void consultarPreco() {
		String codigoProduto;
		Produto produtoBuscado = null;
		Scanner sc = new Scanner(System.in);
		System.out.printf("%s: ", MensagensSistema.INFORME_CODIGO_PRODUTO);
		codigoProduto = sc.next();
		if (codigoProduto.trim().equals("") || !ehValorNumerico(codigoProduto)) {
			System.out.println(MensagensSistema.CODIGO_INVALIDO);
		} else {
			produtoBuscado = Estoque.Instancia().obtenhaProduto(Integer.parseInt(codigoProduto));
			if (produtoBuscado == null) {
				System.out.printf("%s\n\n", MensagensSistema.PRODUTO_NAO_ENCONTRADO);
				return;
			} else {
				System.out.printf("%s: %s.\n", MensagensSistema.DESCRICAO, produtoBuscado.getDescricao());
				System.out.printf("%s: R$%.2f.\n\n", MensagensSistema.VALOR_PRODUTO, produtoBuscado.getPreco());				
			}
		}
	}

	private boolean ehValorNumerico(String valor) {
		return !valor.trim().isEmpty() && StringUtils.isNumeric(valor.trim());
	}
}
