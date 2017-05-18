package br.ufg.inf.produto;

public class Produto {

    private int codigo;
    private String descricao;
    private float preco;


    public Produto() {
    }

    public Produto(int codigo, String descricao, float preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    @Override
    public boolean equals(Object obj) {
    	boolean flag = false;
    	if(obj instanceof Produto){
    		Produto produto = (Produto)obj;
    		flag = produto.getCodigo() == this.getCodigo();
    	}    	
    	
    	return flag;
    }

}
