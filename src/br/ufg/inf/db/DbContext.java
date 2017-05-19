package br.ufg.inf.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;
import br.ufg.inf.venda.Caixa;

public class DbContext {
	public static List<Caixa> Caixas = new ArrayList<Caixa>();
	
	public static Map<Integer, Funcionario> funcionarios(){
		
		Map<Integer, Funcionario> funcionarios = new HashMap<Integer, Funcionario>();
		funcionarios.put(1, new Gerente(1, "Gerente"));
		funcionarios.put(2, new Funcionario(2, "Cláudio"));
		funcionarios.put(3, new Funcionario(3, "Danillo"));
		funcionarios.put(4, new Funcionario(4, "Vinícius"));
		
		return funcionarios;
	}
	
	public static boolean senhaValida(int codigoFuncionario, String senha){
		Map<Integer, String> senhas = new HashMap<Integer, String>();
		senhas.put(1, "123");
		senhas.put(2, "234");
		senhas.put(3, "345");
		senhas.put(4, "456");
		
		String senhaArmazenada = senhas.get(codigoFuncionario);
		return  senhaArmazenada == senha;
	}
}
