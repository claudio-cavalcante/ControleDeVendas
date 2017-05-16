package br.ufg.inf.relatorio;

import br.ufg.inf.produto.Estoque;
import br.ufg.inf.produto.Operacao;
import br.ufg.inf.produto.Produto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RelatorioDeEstoque implements Relatorio{


    public List<Produto> estoqueAtual() {

        List<Produto> listaproduto = new ArrayList<>();

        Set<Map.Entry<Produto, Integer>> set = Estoque.Instancia().estoqueProdutos().entrySet();

        for (Map.Entry<Produto, Integer> produto : set) {
                listaproduto.add(produto.getKey());

            }

        return listaproduto;
    }


    public Map<Produto, Integer> estoqueInicioDia(){

        Map<Produto, Integer> listaproduto = new HashMap<Produto, Integer>();

        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate agora = LocalDate.now();
        // Verifico se o dia do registro e o dia de hoje, se for e estoque do dia
        // retorno para uma nova lista somente os do dia
        Estoque.Instancia().estoqueProdutosHistorico().stream()
                .filter(c -> c.getDataOperacao() != agora )
                .forEach(c -> listaproduto.put(c.getProduto(),c.getQtd_produto()));

        return listaproduto;
    }


    public Map<Produto, Integer>  estoqueFinalDia(){

        Map<Produto, Integer> listaproduto = new HashMap<Produto, Integer>();

        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate agora = LocalDate.now();
        // Verifico se o dia do registro e o dia de hoje, se for e estoque do dia
        // retorno para uma nova lista somente os do dia
        Estoque.Instancia().estoqueProdutosHistorico().stream()
                .filter(c -> c.getDataOperacao().format(formatador).toString() == agora.format(formatador).toString())
                .forEach(c -> listaproduto.put(c.getProduto(),c.getQtd_produto()));

        return listaproduto;
    }



    @Override
	public String emitir() {

		String relatorio = "";

        Set<Map.Entry<Produto, Integer>> set = Estoque.Instancia().estoqueProdutos().entrySet();

        for (Map.Entry<Produto, Integer> produto : set) {

            relatorio += String.format("Código do produto: "+produto.getKey().getCodigo()+"\n" +
                    "Descrição do Produto: "+produto.getKey().getDescricao()+"\n" +
                    "Preço: "+produto.getKey().getPreco()+"\n"+
                    "Quantidade: "+produto.getValue()+"\n\n" );

        }
        return relatorio;
	}
}
