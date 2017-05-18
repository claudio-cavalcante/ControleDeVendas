package br.ufg.inf.menuV2;
import java.util.function.Supplier;

public interface IOpcaoMenu {
	String getNome();
	Supplier<Boolean> getAcao();
	boolean autorizadoParaFuncionario();
}
