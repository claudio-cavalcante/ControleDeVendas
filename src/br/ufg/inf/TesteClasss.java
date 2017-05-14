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
		Caixa caixa = new Caixa("1", new Funcionario("Joao"));
		RelatorioDeVendas r = new RelatorioDeVendas(caixa);

		Funcionario funcionario = new Funcionario("Fulano1");
		Produto produto = new Produto(1, "Computador", 2000);
		Funcionario gerente = new Funcionario("Fulano2");
		Produto produto2 = new Produto(1, "Bolacha", 2);

		Estoque estoque = new Estoque();

		estoque.adicionar(funcionario, produto, 20);
		estoque.adicionar(funcionario, produto2, 60);

		RelatorioDeEstoque re = new RelatorioDeEstoque();

		estoque.estoqueProdutos().forEach(c -> System.out.println(c.getProduto().getDescricao()));

		System.out.println(re.emitir());
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

}
