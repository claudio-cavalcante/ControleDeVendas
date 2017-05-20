package br.ufg.inf.exception;

import br.ufg.inf.menu.MensagensSistema;

public class ProdutoNaoCadastradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoNaoCadastradoException(){
		super(MensagensSistema.PRODUTO_NAO_CADASTRADO);
	};
}
