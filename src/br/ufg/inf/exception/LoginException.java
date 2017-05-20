package br.ufg.inf.exception;

import br.ufg.inf.menu.MensagensSistema;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginException(){
		super(MensagensSistema.DADOS_LOGIN_INVALIDOS);
	}
}
