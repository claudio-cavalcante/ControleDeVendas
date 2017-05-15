package br.ufg.inf.produto;

import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.pessoa.Gerente;
import kotlin.internal.InlineOnly;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by vinicius on 13/05/17.
 */

public class Estoque {

    List<Operacao> historicooperacao = new ArrayList<>();

    Map<Produto, Integer> produtosEmEstoque = new HashMap<Produto, Integer>();

    public void adicionar(Funcionario funcionario,Produto produto,int qtd) {

        if (produtosEmEstoque.containsKey(produto)) {
            int qtdAnterior = produtosEmEstoque.get(produto);
            produtosEmEstoque.replace(produto, produtosEmEstoque.get(produto), (produtosEmEstoque.get(produto) + qtd));
        } else {
            produtosEmEstoque.put(produto, qtd);
        }


        // PARA FINS DE LOG
        // if(funcionario instanceof Gerente){

            DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate agora = LocalDate.now();

            Operacao op = new Operacao(1, produto, qtd, agora);
            historicooperacao.add(op);

        //}
    }


    public  List<Operacao> estoqueProdutosHistorico(){

        return historicooperacao;
    }


    public Map<Produto, Integer> estoqueProdutos(){

        return produtosEmEstoque;
    }

    public void remover(Produto produto, int qtd){

        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate agora = LocalDate.now();


        if(produtosEmEstoque.containsKey(produto)) {
            if (produtosEmEstoque.get(produto) > qtd) {
                produtosEmEstoque.put(produto, (produtosEmEstoque.get(produto) - qtd));

                Operacao op = new Operacao(0, produto, (produtosEmEstoque.get(produto) - qtd), agora);
                historicooperacao.add(op);

            } else
                System.out.println("Não existe produto em quantidade suficiente em estoque!");

        }
        else
            System.out.println("Não existe produto no estoque");

    }
}
