package br.ufg.inf.relatorio;

import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Produto;

import java.util.*;

public class RelatorioDeEstoque implements Relatorio{

	Estoque estoque = new Estoque();


    public void estoqueAtual() {


    }


    public void estoqueDoDia(){

    }


    @Override
	public String emitir() {
		String relatorio = "";

        Set<Map.Entry<Produto, Integer>> set = estoque.estoqueProdutos().entrySet();

        for (Map.Entry<Produto, Integer> produto : set) {

            relatorio += String.format("Código do produto: "+produto.getKey().getCodigo()+"\n" +
                    "Descrição do Produto: "+produto.getKey().getDescricao()+"\n" +
                    "Preço: "+produto.getKey().getPreco()+"\n"+
                    "Quantidade: "+produto.getValue() );

        }
  		return relatorio;
	}

}
