package br.ufg.inf.menu;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;

public enum EnumFuncoesDoSistema {
	REALIZAR_VENDA, CONSULTAR_PRECO, ADICIONAR_PRODUTO_ESTOQUE, EMITIR_RELATORIO_VENDA, EMITIR_RELATORIO_ESTOQUE, LOGOFF, SAIR;

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
		funcoes.put(REALIZAR_VENDA, MensagensSistemaDeVendas.REALIZAR_VENDA);
		funcoes.put(CONSULTAR_PRECO, MensagensSistemaDeVendas.CONSULTAR_PRECO);
		if (funcionario.getClass().getName() == Gerente.class.getName()) {
			funcoes.put(ADICIONAR_PRODUTO_ESTOQUE, MensagensSistemaDeVendas.ADICIONAR_PRODUTO_ESTOQUE);
			funcoes.put(EMITIR_RELATORIO_VENDA, MensagensSistemaDeVendas.EMITIR_RELATORIO_VENDA);
			funcoes.put(EMITIR_RELATORIO_ESTOQUE, MensagensSistemaDeVendas.EMITIR_RELATORIO_ESTOQUE);
		}
		
		funcoes.put(LOGOFF, MensagensSistemaDeVendas.LOGOFF);
		funcoes.put(SAIR, MensagensSistemaDeVendas.SAIR);
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
