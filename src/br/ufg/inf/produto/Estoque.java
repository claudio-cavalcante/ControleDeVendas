package br.ufg.inf.produto;

import br.ufg.inf.pessoa.Funcionario;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vinicius on 13/05/17.
 */

public class Estoque {



    Map<Produto, Integer> produtosEmEstoque = new HashMap<Produto, Integer>();

    public void adicionar(Funcionario funcionario,Produto produto,int qtd){

        if(produtosEmEstoque.containsKey(produto)){
            int qtdAnterior = produtosEmEstoque.get(produto);
            produtosEmEstoque.replace(produto,produtosEmEstoque.get(produto),(produtosEmEstoque.get(produto)+qtd) );
        }
       else{
            produtosEmEstoque.put(produto,qtd );
        }


    }

  
    public void remover(Produto produto, int qtd){

        if(produtosEmEstoque.containsKey(produto)) {
            produtosEmEstoque.put(produto, (produtosEmEstoque.get(produto) - qtd));
        }
        else
            System.out.println("Não existe produto no estoque");
    }


}
