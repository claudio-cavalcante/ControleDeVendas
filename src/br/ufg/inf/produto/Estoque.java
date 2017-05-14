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


    public void adicionar(Funcionario funcionario,Produto produto,int qtd) {

        if(funcionario instanceof Gerente){


        }

        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate agora = LocalDate.now();

        Operacao op = new Operacao(1, produto, qtd, agora);
        historicooperacao.add(op);

    }


    public List<Operacao> estoqueProdutos(){

        return historicooperacao;
    }


    public void remover(Produto produto, int qtd){

        for(Operacao operacaos: historicooperacao){

            if(operacaos.getProduto().equals(produto)){

                if(operacaos.getQtd_produto()>qtd){

                    operacaos.setQtd_produto(operacaos.getQtd_produto()-qtd);
                }
                else
                    System.out.println("Não existe produto em quantidade suficiente em estoque!");

            }
            else
                System.out.println("Não existe produto no estoque");
        }
    }
}
