package br.com.square.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.square.dao.ProdutoDao;
import br.com.square.modelo.Produto;

@Named
@RequestScoped
public class ProdutoBean implements Serializable {
	private Produto produto = new Produto();
	private List<Produto> produtos;

	@Inject
	private ProdutoDao produtoDao;

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		if (this.produtos == null) {
			this.listaTodos();
		}
		return produtos;
	}
	
	public void salva() {
		if(this.produto.getId() == 0L) {
			this.produtoDao.adiciona(this.produto);
		} else {
			this.produtoDao.atualiza(produto);
		}
		
		this.listaTodos();
		this.limpa();
	}

	private void listaTodos() {
		this.produtos = this.produtoDao.lista();
	}
	
	private void limpa() {
		this.produto = new Produto();
	}
}
