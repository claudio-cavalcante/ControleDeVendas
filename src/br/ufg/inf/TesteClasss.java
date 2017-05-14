package br.ufg.inf;

import java.util.ArrayList;
import java.util.List;

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
		Caixa caixa = new Caixa("1", new Funcionario("João"));
		RelatorioDeVendas r = new RelatorioDeVendas(caixa);
		
		Produto produto = new Produto(1, "Computador", 2000);
		
		
		List<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
		ItemVenda itemVenda = new ItemVenda(produto, 2);
		itensVenda.add(itemVenda);
		
		Venda venda = new Venda(itensVenda, new FormaDePagamentoEmDinheiro());					
		IProcessamentoDoPagamento processamento = venda.realizarPagamento();
		if(processamento.pagamentoRealizadoComSucesso()){
			System.out.println("Venda concluída" + (processamento.houveTroco() ? " com " + processamento.valorDoTroco() + "de troco" : "" ));
			caixa.registrarVenda(venda);
		} else{
			System.out.println(processamento.mensagem());
		}
		
		System.out.println(r.emitir());
		
		
		
	}

}
