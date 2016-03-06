package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@ManagedBean
public class ProdutoBean {
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private double total;
	
	public List<Produto> getProdutos() {
		if(this.produtos == null) {
			System.out.println("Carregando os produtos...");
			this.produtos = new ProdutoDao().listaTodos();
		}
		return produtos;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
/*	public double getTotal() {
		if(this.produtos == null) return 0;
		
		for (Produto produto : produtos) {
			this.total += produto.getPreco();
		}
		
		return this.total;
	}*/
	
	public void grava() {
		ProdutoDao dao = new ProdutoDao();
		
		if(this.produto.getId() == null) {
			dao.adiciona(produto);			
		} else {
			dao.atualiza(produto);
		}
		
		this.produto = new Produto();
		this.produtos = dao.listaTodos();
	}
	
	public void remove(Produto produto) {
		ProdutoDao dao = new ProdutoDao();
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
	
}
