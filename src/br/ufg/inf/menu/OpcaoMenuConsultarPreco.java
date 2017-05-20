package br.ufg.inf.menu;

import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.Utilitarios;
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
		return new EnumPapel[] { EnumPapel.GERENTE, EnumPapel.FUNCIONARIO };
	}

	private void consultarPreco() {
		String codigoProduto;
		Produto produtoBuscado = null;
		
		System.out.printf("%s: ", MensagensSistema.INFORME_CODIGO_PRODUTO);
		
		Scanner sc = new Scanner(System.in);
		codigoProduto = sc.next();
		if (codigoProduto.trim().equals("") || !Utilitarios.ehValorNumerico(codigoProduto)) {
			System.out.printf("%s\n\n", MensagensSistema.CODIGO_INVALIDO);
		} else {
			produtoBuscado = Estoque.getInstancia().getProduto(Integer.parseInt(codigoProduto));
			if (produtoBuscado == null) {
				System.out.printf("%s\n\n", MensagensSistema.PRODUTO_NAO_ENCONTRADO);
				return;
			} else {
				System.out.printf("%s: %s.\n", MensagensSistema.DESCRICAO, produtoBuscado.getDescricao());
				System.out.printf("%s: R$%.2f.\n\n", MensagensSistema.VALOR_PRODUTO, produtoBuscado.getPreco());				
			}
		}
	}	
}
