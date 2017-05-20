package br.ufg.inf.menu;
import java.util.function.Supplier;

public interface IOpcaoMenu {
	String getNome();
	Supplier<Boolean> getAcao();
	EnumPapel[] papeisAutorizados();
}
