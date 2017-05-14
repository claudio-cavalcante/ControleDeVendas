package br.ufg.inf.produto;

import br.ufg.inf.pessoa.Funcionario;

import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by vinicius on 13/05/17.
 */

public class EstoqueHistorico {


    Map<Object, LocalDate> produtosEmEstoqueHistoricoTempo = new HashMap<Object, LocalDate>();

    public void adicionar(Map<Produto, Integer> estoque) {

        produtosEmEstoqueHistoricoTempo.put(estoque, LocalDate.now());

    }
/*
    public Map<Produto, Integer> estoqueProdutosDiaAnterior(){

        Set<Map.Entry<Object, LocalDate>> set = produtosEmEstoqueHistoricoTempo.entrySet();


        Map<Produto, Integer> produtosEmEstoque = new HashMap<Produto, Integer>();


        for (Map.Entry<Object, LocalDate> produto : set) {



        }

        return produtosEmEstoque;
    }
*/
    public Map<Object, LocalDate> estoqueProdutosDiaAnterior(){

    return produtosEmEstoqueHistoricoTempo;
    }

}
