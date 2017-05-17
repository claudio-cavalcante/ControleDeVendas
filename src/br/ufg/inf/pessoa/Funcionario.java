package br.ufg.inf.pessoa;

public class Funcionario {
	private String nome;
	private int matricula;

	public Funcionario(int matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	public int getMatricula() {
		return this.matricula;
	}
	public String getNome() {
		return this.nome;
	}
}
