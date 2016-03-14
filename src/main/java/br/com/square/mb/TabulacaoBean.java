package br.com.square.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.square.dao.ArvoreDao;
import br.com.square.dao.ProdutoDao;
import br.com.square.dao.SiteDao;
import br.com.square.dao.TabulacaoDao;
import br.com.square.dao.UsuarioDao;
import br.com.square.datamodel.DataModelTabulacoes;
import br.com.square.dto.ListaDeTabulacoesDTO;
import br.com.square.enums.Rechamada;
import br.com.square.modelo.Arvore;
import br.com.square.modelo.Produto;
import br.com.square.modelo.Site;
import br.com.square.modelo.Tabulacao;
import br.com.square.modelo.Usuario;

@Named
@ViewScoped
public class TabulacaoBean implements Serializable {
	private Tabulacao tabulacao = new Tabulacao();
	private List<String> motivos;
	private List<String> subMotivos;
	private List<String> detalhes;
	private List<ListaDeTabulacoesDTO> tabulacoes;

	private long produtoSelecionado;
	private String motivoSelecionado;
	private String subMotivoSelecionado;
	private String detalheSelecionado;
	private long siteSelecionado;

	@Inject
	private ArvoreDao arvoreDao;

	@Inject
	private TabulacaoDao tabulacaoDao;

	@Inject
	ProdutoDao produtoDao;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private SiteDao siteDao;
	
	@Inject
	private DataModelTabulacoes dataModel;

	// Recupera o Usuário logado na Sessão...
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public Tabulacao getTabulacao() {
		return tabulacao;
	}

	public long getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(long produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public String getMotivoSelecionado() {
		return motivoSelecionado;
	}

	public void setMotivoSelecionado(String motivoSelecionado) {
		this.motivoSelecionado = motivoSelecionado;
	}

	public String getSubMotivoSelecionado() {
		return subMotivoSelecionado;
	}

	public void setSubMotivoSelecionado(String subMotivoSelecionado) {
		this.subMotivoSelecionado = subMotivoSelecionado;
	}

	public String getDetalheSelecionado() {
		return detalheSelecionado;
	}

	public void setDetalheSelecionado(String detalheSelecionado) {
		this.detalheSelecionado = detalheSelecionado;
	}

	public List<String> getMotivos() {
		this.motivos = this.arvoreDao.listaMotivos(this.produtoSelecionado);
		return motivos;
	}

	public List<String> getSubMotivos() {
		this.subMotivos = this.arvoreDao.listaSubMotivos(
				this.produtoSelecionado, this.motivoSelecionado);
		return subMotivos;
	}

	public List<String> getDetalhes() {
		this.detalhes = this.arvoreDao.listaDetalhes(this.produtoSelecionado,
				this.motivoSelecionado, this.subMotivoSelecionado);
		return detalhes;
	}

	public List<ListaDeTabulacoesDTO> getTabulacoes() {
		if (this.tabulacoes == null) {
			this.tabulacoes = this.tabulacaoDao.lista();
		}
		return tabulacoes;
	}

	public Rechamada[] getRechamadas() {
		return Rechamada.values();
	}
	
	public DataModelTabulacoes getDataModel() {
		return dataModel;
	}

	public String limpa() {
		return "tabulador?faces-redirect=true";
	}

	public String tabula() {
		Produto produto = this.produtoDao.buscaPorId(this.produtoSelecionado);

		Arvore arvore = this.arvoreDao.buscaPorAvoreCompleta(produto,
				this.motivoSelecionado, this.subMotivoSelecionado,
				this.detalheSelecionado);

		// RECUPERA O USUÁRIO DA SESSÃO...
		Usuario usuario = this.usuarioLogado.getUsuario();

		Site site = this.siteDao.buscaPorId(this.siteSelecionado);

		// INSERE OS RELACIONAMENTOS...
		this.insereRelacionamentos(produto, arvore, usuario, site);

		this.tabulacaoDao.adiciona(tabulacao);

		// Redireciona para a mesma página para resetar os inputs.
		return "tabulador?faces-redirect=true";
	}

	private void insereRelacionamentos(Produto produto, Arvore arvore,
			Usuario usuario, Site site) {
		this.tabulacao.setProduto(produto);
		this.tabulacao.setArvore(arvore);
		this.tabulacao.setUsuario(usuario);
		this.tabulacao.setSite(site);
	}

	public long getSiteSelecionado() {
		return siteSelecionado;
	}

	public void setSiteSelecionado(long siteSelecionado) {
		this.siteSelecionado = siteSelecionado;
	}

}
