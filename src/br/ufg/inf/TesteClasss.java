package br.ufg.inf;

import java.sql.SQLSyntaxErrorException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.pessoa.Gerente;
import br.ufg.inf.relatorio.RelatorioDeEstoque;
import br.ufg.inf.relatorio.RelatorioDeVendas;
import br.ufg.inf.venda.*;
import br.ufg.inf.pagamento.FormaDePagamentoEmDinheiro;
import br.ufg.inf.pagamento.IProcessamentoDoPagamento;
import br.ufg.inf.pessoa.Funcionario;
import br.ufg.inf.produto.*;

/**
 * Created by vinicius on 13/05/17.
 */
public class TesteClasss {
	public static void main(String args[]){
		Caixa caixa = new Caixa("1", new Funcionario(4,"Joao"));
		RelatorioDeVendas r = new RelatorioDeVendas(caixa);

		Estoque estoque = new Estoque();
		RelatorioDeEstoque re = new RelatorioDeEstoque();


		Funcionario funcionario = new Funcionario(1,"Fulano1");
		Produto produto = new Produto(1, "Computador", 2000);
		Funcionario funcionario2 = new Gerente(2,"Fulano2");
		Produto produto2 = new Produto(2, "Bolacha", 2);
		Funcionario funcionario3 = new Gerente(3,"Fulano3");
		Produto produto3 = new Produto(3, "Biscoito", 8);

		estoque.adicionar(funcionario, produto, 20);
		estoque.adicionar(funcionario2, produto2, 60);
		estoque.adicionar(funcionario3, produto3, 10);
		estoque.adicionar(funcionario3, produto3, 8);
		estoque.adicionar(funcionario3, produto3, 5);

		//estoque.estoqueProdutos().forEach(c -> imprimir(c.getProduto()));

//		estoque.estoqueProdutos().forEach((k,v) ->  System.out.println(v));

		estoque.remover( produto2, 2);

	//	estoque.estoqueProdutos().forEach((k,v) ->  System.out.println(v));


		//System.out.println(estoque.emitir());


		System.out.println("Antes do Dia");

		re.estoqueInicioDoDia().forEach((k,v) -> imprimir(k,v));



		System.out.println("Final do Dia");

		re.estoqueFinalDoDia().forEach((k,v) -> imprimir(k,v));



/*
		List<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
		ItemVenda itemVenda = new ItemVenda(produto, 2);
		itensVenda.add(itemVenda);
		
		Venda venda = new Venda(itensVenda, new FormaDePagamentoEmDinheiro());					
		IProcessamentoDoPagamento processamento = venda.realizarPagamento(5000);
				
		if(processamento.pagamentoRealizadoComSucesso()){
			System.out.println("Venda concluida" + (processamento.houveTroco() ? " com " + processamento.valorDoTroco() + " de troco" : "" ));
			caixa.registrarVenda(venda);


		} else{
			System.out.println(processamento.mensagem());
		}
		
		System.out.println(r.emitir());
		
		
*/
	}

	public static void imprimir(Produto produto, int v){

		System.out.println("Codigo: "+produto.getCodigo());
		System.out.println("Descricao: "+produto.getDescricao());
		System.out.println("Preço: "+produto.getPreco());
		System.out.println("Quantidade: "+v+"\n");
	}


}
