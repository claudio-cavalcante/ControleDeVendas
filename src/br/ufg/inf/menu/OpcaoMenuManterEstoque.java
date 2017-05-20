package br.ufg.inf.menu;

import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.ufg.inf.menu.MensagensSistema;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;

public class OpcaoMenuManterEstoque implements IOpcaoMenu {

	@Override
	public String getNome() {
		return MensagensSistema.ADICIONAR_MANTER_PRODUTO_ESTOQUE;
	}

	@Override
	public Supplier<Boolean> getAcao() {

		adicionarProdutoNoEstoque();
		return () -> true;
	}

	@Override
	public EnumPapel[] papeisAutorizados() {
		return new EnumPapel[] { EnumPapel.GERENTE };
	}

	private void adicionarProdutoNoEstoque() {
		Scanner sc = new Scanner(System.in);
		String opcao;
		do {
			System.out.printf("0 - %s\n", MensagensSistema.ADICIONAR_PRODUTO);
			System.out.printf("1 - %s\n", MensagensSistema.REPOR_PRODUTO);
			opcao = sc.nextLine();
		} while (!opcao.equals("0") && !opcao.equals("1"));

		boolean novoProduto = opcao.equals("0");		
		System.out.println(MensagensSistema.FAVOR_INFORMAR_DADOS_PRODUTO);
		System.out.printf("%s: ", MensagensSistema.CODIGO);
		boolean codigoValido = false;
		String codigo;
		do {
			codigo = sc.next();
			codigoValido = StringUtils.isNumeric(codigo);
			if (!codigoValido) {
				System.out.printf("%s: ", MensagensSistema.CODIGO_INVALIDO);
			}
		} while (!codigoValido);

		String descricao = "";
		String preco = "0";
		Produto produto;
		if (!novoProduto) {
			produto = Estoque.Instancia().getProduto(Integer.parseInt(codigo));
			if (produto == null) {
				System.out.println(MensagensSistema.PRODUTO_NAO_ENCONTRADO);
				return;
			}
		} else {
			System.out.printf("%s: ", MensagensSistema.DESCRICAO);
			descricao = sc.next();
			System.out.printf("%s: ", MensagensSistema.PRECO);
			boolean precoValido;
			do {
				preco = sc.next();
				precoValido = NumberUtils.isParsable(preco);
				if (!precoValido) {
					System.out.printf("%s: ", MensagensSistema.PRECO_INVALIDO);
				}
			} while (!precoValido);
		}

		System.out.printf("%s: ", MensagensSistema.QUANTIDADE);
		boolean qtdValida;
		String qtd;
		do {
			qtd = sc.next();
			qtdValida = NumberUtils.isParsable(qtd);
			if (!qtdValida) {
				System.out.printf("%s: ", MensagensSistema.QUANTIDADE_INVALIDA);
			}
		} while (!qtdValida);

		if (novoProduto) {
			produto = new Produto(Integer.parseInt(codigo), descricao, Float.parseFloat(preco));
			boolean produtoAdicionado = Estoque.Instancia().adicionar(produto, Integer.parseInt(qtd));
			if (produtoAdicionado) {
				System.out.printf("\n%s\n", MensagensSistema.PRODUTO_ADICIONADO_ESTOQUE);
			} else {
				System.out.printf("%s\n\n", MensagensSistema.PRODUTO_JA_ADICIONADO);
			}
		} else {
			Estoque.Instancia().repor(Integer.parseInt(codigo), Integer.parseInt(qtd));
			System.out.printf("\n%s\n", MensagensSistema.PRODUTO_REPOSTO);
		}
	}
}
