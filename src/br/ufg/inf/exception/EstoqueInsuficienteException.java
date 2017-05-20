package br.ufg.inf.exception;

import br.ufg.inf.menu.MensagensSistema;

public class EstoqueInsuficienteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EstoqueInsuficienteException(){
		super(MensagensSistema.QUANTIDADE_INSUFICIENTE);
	};
}
