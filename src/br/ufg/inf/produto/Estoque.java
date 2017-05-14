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


    // Criei 2 maps pois os mesmos so recebem 2 objetos com parametros

    Map<Funcionario, Object> produtosEmEstoque = new HashMap<Funcionario, Object>();
    Map<Produto, Integer> produtosEmEstoque2 = new HashMap<Produto, Integer>();


    public void adicionar(Funcionario funcionario,Produto produto,int qtd){

        try {

        produtosEmEstoque2.put(produto,qtd );
        produtosEmEstoque.put(funcionario, produtosEmEstoque2);

        } catch(Exception e){e.printStackTrace();}

    }

    // para fins de consulta de adicionar produto e remover produtos
    public void MostrarEstoque(){

        try {

        produtosEmEstoque2.forEach((k,v)->System.out.println("Item : " + k.getNome() + " Quantidade : " + v));

        } catch(Exception e){e.printStackTrace();}

    }



    public void remover(Produto produto){

        try {

            produtosEmEstoque2.remove(produto);

        } catch(Exception e){e.printStackTrace();}

    }






}
