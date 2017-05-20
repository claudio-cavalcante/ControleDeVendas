package br.ufg.inf;

import org.apache.commons.lang3.StringUtils;

public class Utilitarios {
	public static boolean ehValorNumerico(String valor) {
		return !valor.trim().isEmpty() && StringUtils.isNumeric(valor.trim());
	}
}
