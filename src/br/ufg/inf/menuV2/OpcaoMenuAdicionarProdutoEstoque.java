package br.ufg.inf.menuV2;
import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.ufg.inf.menu.MensagensSistemaDeVendas;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;

public class OpcaoMenuAdicionarProdutoEstoque  implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistemaDeVendas.ADICIONAR_PRODUTO_ESTOQUE;
	}

	@Override
	public Supplier<Boolean> getAcao() {
		adicionarProdutoNoEstoque();
		return () -> true;
	}
	
	@Override
	public EnumPapel[] papeisAutorizados() {
		return new EnumPapel[]{ EnumPapel.GERENTE };
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
		Estoque.Instancia().adicionar(produto, Integer.parseInt(qtd));
		System.out.println(MensagensSistemaDeVendas.PRODUTO_ADICIONADO_ESTOQUE);
	}
}
