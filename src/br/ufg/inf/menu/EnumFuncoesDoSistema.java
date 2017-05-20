package br.ufg.inf.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;

public enum EnumFuncoesDoSistema {
	SAIR, LOGOFF, REALIZAR_VENDA, CONSULTAR_PRECO, ADICIONAR_PRODUTO_ESTOQUE, EMITIR_RELATORIO_VENDA, EMITIR_RELATORIO_ESTOQUE;

	public static EnumFuncoesDoSistema getFuncao(int opcao) {
		for (EnumFuncoesDoSistema funcao : EnumFuncoesDoSistema.values()) {
			if (funcao.ordinal() == opcao) {
				return funcao;
			}
		}

		return null;
	}

	public static Map<EnumFuncoesDoSistema, String> obtenhaFuncoes(Funcionario funcionario) {
		Map<EnumFuncoesDoSistema, String> funcoes = new LinkedHashMap<EnumFuncoesDoSistema, String>();		
		funcoes.put(SAIR, MensagensSistema.SAIR);
		funcoes.put(LOGOFF, MensagensSistema.LOGOFF);
		funcoes.put(REALIZAR_VENDA, MensagensSistema.REALIZAR_VENDA);
		funcoes.put(CONSULTAR_PRECO, MensagensSistema.CONSULTAR_PRECO);
		if (funcionario instanceof Gerente) {
			funcoes.put(ADICIONAR_PRODUTO_ESTOQUE, MensagensSistema.ADICIONAR_MANTER_PRODUTO_ESTOQUE);
			funcoes.put(EMITIR_RELATORIO_VENDA, MensagensSistema.EMITIR_RELATORIO_VENDA);
			funcoes.put(EMITIR_RELATORIO_ESTOQUE, MensagensSistema.EMITIR_RELATORIO_ESTOQUE);
		}

		return funcoes;
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
