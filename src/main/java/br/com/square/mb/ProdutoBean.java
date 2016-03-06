package br.com.square.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.square.dao.ProdutoDao;
import br.com.square.modelo.Produto;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {
	private Produto produto = new Produto();
	private List<Produto> produtos;

	@Inject
	private ProdutoDao produtoDao;

	public Produto getProduto() {
		return produto;
	}

	public List<Produto> getProdutos() {
		if (this.produtos == null) {
			this.listaTodos();
		}
		return produtos;
	}

	private void listaTodos() {
		this.produtos = this.produtoDao.lista();
	}
}
