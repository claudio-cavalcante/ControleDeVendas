package br.ufg.inf.menu;

public enum EnumFuncoesDoSistema {
	SAIR, ADICIONAR_PRODUTO_ESTOQUE, REALIZAR_VENDA, CONSULTAR_PRECO, EMITIR_RELATORIO_VENDA, EMITIR_RELATORIO_ESTOQUE;

	public static EnumFuncoesDoSistema getFuncao(int opcao) {
		for (EnumFuncoesDoSistema funcao : EnumFuncoesDoSistema.values()) {
			if (funcao.ordinal() == opcao) {
				return funcao;
			}
		}
		
		return null;
	}
	
	public static boolean funcaoEhValida(String opcao) {
		for (EnumFuncoesDoSistema funcao : EnumFuncoesDoSistema.values()) {
			if (Integer.toString(funcao.ordinal()).equals(opcao.trim())) {
				return true;
			}
		}

		return false;
	}
}
