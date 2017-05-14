package br.ufg.inf.relatorio;

import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Operacao;
import br.ufg.inf.produto.Produto;

import java.util.*;

public class RelatorioDeEstoque implements Relatorio{

	Estoque estoque = new Estoque();

    public List<Produto> estoqueAtual() {

            List<Produto> listaproduto = new ArrayList<>();

            for(Operacao operacao: estoque.estoqueProdutos()) {

                listaproduto.add(operacao.getProduto());

            }

        return listaproduto;
    }


    public List<Produto> estoqueDoDia(){

        List<Produto> listaproduto = new ArrayList<>();

        for(Operacao operacao: estoque.estoqueProdutos()) {

            listaproduto.add(operacao.getProduto());

        }

        return listaproduto;
    }


    @Override
	public String emitir() {
		String relatorio = "";

        for(Operacao operacao: estoque.estoqueProdutos()) {

            relatorio += String.format("Código do produto: "+operacao.getProduto().getCodigo()+"\n" +
                    "Descrição do Produto: "+operacao.getProduto().getDescricao()+"\n" +
                    "Preço: "+operacao.getProduto().getPreco()+"\n"+
                    "Quantidade: "+operacao.getQtd_produto() );


        }

  		return relatorio;
	}

}
