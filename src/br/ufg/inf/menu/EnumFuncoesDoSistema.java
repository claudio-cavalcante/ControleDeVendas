package br.ufg.inf.menu;

public enum EnumFuncoesDoSistema {
	SAIR,
	
	ADICIONAR_PRODUTO;
	
	public static boolean funcaoEhValida(String opcao) {
		for (EnumFuncoesDoSistema funcao : EnumFuncoesDoSistema.values()) {
			if (Integer.toString(funcao.ordinal()).equals(opcao.trim())) {
				return true;
			}
		}
		
		return false;
	}
}
