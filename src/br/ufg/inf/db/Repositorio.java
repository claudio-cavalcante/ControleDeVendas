package br.ufg.inf.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.ufg.inf.Utilitarios;
import br.ufg.inf.exception.LoginException;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;
import br.ufg.inf.venda.Caixa;

public class Repositorio {

	private static Repositorio dbContext = new Repositorio();

	private Map<Integer, Funcionario> funcionarios;
	private Map<Integer, Caixa> caixas;

	private Repositorio() {
		funcionarios = new HashMap<Integer, Funcionario>();
		funcionarios.put(1, new Gerente(1, "Gerente"));
		funcionarios.put(2, new Funcionario(2, "Cláudio"));
		funcionarios.put(3, new Funcionario(3, "Danillo"));
		funcionarios.put(4, new Funcionario(4, "Vinícius"));
		funcionarios.put(5, new Funcionario(5, "Daniel"));
		funcionarios.put(6, new Funcionario(6, "João"));

		caixas = new HashMap<Integer, Caixa>();
		caixas.put(1, new Caixa("1"));
		caixas.put(2, new Caixa("2"));
		caixas.put(3, new Caixa("3"));
	}

	public static Repositorio getInstancia() {
		return dbContext;
	}

	public Map<Integer, Funcionario> funcionarios() {
		return funcionarios;
	}

	public Map<Integer, Caixa> caixas() {
		return caixas;
	}

	public void efetueLogin(String login, String senha) throws LoginException {
		Map<Integer, String> senhas = new HashMap<Integer, String>();
		senhas.put(1, "123");
		senhas.put(2, "234");
		senhas.put(3, "345");
		senhas.put(4, "456");
		senhas.put(5, "567");
		senhas.put(6, "678");
		
		if(!Utilitarios.ehValorNumerico(login)){
			throw new LoginException();
		}

		String senhaArmazenada = senhas.get(Integer.parseInt(login));
		if(senhaArmazenada == null || senhaArmazenada.equals(senha)){
			throw new LoginException();
		};
	}	
}
