package br.com.square.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.square.dao.ArvoreDao;
import br.com.square.dao.ProdutoDao;
import br.com.square.datamodel.DataModelArvore;
import br.com.square.dto.ListaDeArvoreComProdutosDTO;
import br.com.square.modelo.Arvore;
import br.com.square.modelo.Produto;

@Named
@ViewScoped
public class ArvoreBean implements Serializable{
	private Arvore arvore = new Arvore();

	private ListaDeArvoreComProdutosDTO arvoreComProduto = new ListaDeArvoreComProdutosDTO();

	private List<ListaDeArvoreComProdutosDTO> arvores;

	private String produtoSelecionado;

	@Inject
	private ProdutoDao produtoDao;

	@Inject
	private ArvoreDao arvoreDao;
	
	@Inject
	private DataModelArvore dataModel;

	public Arvore getArvore() {
		return arvore;
	}

	public void setArvore(Arvore arvore) {
		this.arvore = arvore;
	}

	public String getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(String produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public void setArvoreComProduto(ListaDeArvoreComProdutosDTO arvoreComProduto) {
		this.arvoreComProduto = arvoreComProduto;
	}

	public ListaDeArvoreComProdutosDTO getArvoreComProduto() {
		return arvoreComProduto;
	}
	
	public DataModelArvore getDataModel() {
		return dataModel;
	}

	public void salva() {
		Produto produto = produtoDao.buscaPorNome(this.produtoSelecionado);

		this.populaArvore();

		if (this.arvore.getId() == 0L) {
			produto.addArvore(this.arvore);
			this.arvoreDao.adiciona(this.arvore);
			this.produtoDao.adiciona(produto);
		} else {
			this.arvoreDao.atualiza(this.arvore);
		}

		this.limpa();
	}

	public List<ListaDeArvoreComProdutosDTO> getArvores() {
		if (this.arvores == null) {
			this.arvores = this.arvoreDao.listaComProdutos();
		}
		return arvores;
	}

	private void populaArvore() {
		this.arvore.setId(this.arvoreComProduto.getIdArvore());
		this.arvore.setMotivo(this.arvoreComProduto.getMotivo());
		this.arvore.setDetalhe(this.arvoreComProduto.getDetalhe());
		this.arvore.setSubmotivo(this.arvoreComProduto.getSubmotivo());
		this.arvore.setAtivo(this.arvoreComProduto.isAtivo());
	}

	private void limpa() {
		this.arvore = new Arvore();
		this.arvoreComProduto = new ListaDeArvoreComProdutosDTO();
		this.produtoSelecionado = null;
	}

}
