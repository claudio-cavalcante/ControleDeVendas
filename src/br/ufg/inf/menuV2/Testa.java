package br.ufg.inf.menuV2;

import java.util.Scanner;

public class Testa {

	public static void main(String[] args) {
		Menu telaInicial = new Menu( new SairOpcaoMenu(),new LoginMenu());
		telaInicial.exibaOpcoes();
		
	
	}

}
