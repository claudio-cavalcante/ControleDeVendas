package br.ufg.inf.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufg.inf.db.Sessao;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;
import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;
import br.ufg.inf.venda.Caixa;

public class Testa {
	private static List<Funcionario> funcionarios;
	private static List<Caixa> caixas;
	
	public static void main(String[] args) {
		populeDados();
		Menu telaInicial = new Menu(new OpcaoMenuSair(), new OpcaoMenuLogin(), new OpcaoMenuConsultarPreco());
		telaInicial.exibaOpcoes();
	}

	private static void populeDados() {
		funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Gerente(1, "Gerente"));
		funcionarios.add(new Funcionario(2, "Cláudio"));
		funcionarios.add(new Funcionario(3, "Danillo"));
		funcionarios.add(new Funcionario(4, "Vinícius"));
		caixas = new ArrayList<Caixa>();
		Estoque.Instancia().adicionar(new Produto(1, "Leite", 5), 10);
		Estoque.Instancia().adicionar(new Produto(2, "Ovos", 12), 10);
		Estoque.Instancia().adicionar(new Produto(2, "Farinha", 2), 100);
	}
}
