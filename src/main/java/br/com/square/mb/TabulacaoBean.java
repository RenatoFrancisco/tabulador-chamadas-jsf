package br.com.square.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.square.dao.ArvoreDao;
import br.com.square.dao.ProdutoDao;
import br.com.square.dao.SiteDao;
import br.com.square.dao.TabulacaoDao;
import br.com.square.dao.UsuarioDao;
import br.com.square.datamodel.DataModelTabulacoes;
import br.com.square.dto.ListaDeTabulacoesDTO;
import br.com.square.enums.Periodo;
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
	
	@NotNull(message = "Produto deve ser selecionado.")
	private long produtoSelecionado;

	@NotEmpty(message = "Motivo deve ser selecionado.")
	private String motivoSelecionado;

	@NotEmpty(message = "Submotivo deve ser selecionado.")
	private String subMotivoSelecionado;

	@NotEmpty(message = "Detalhe deve ser selecionado.")
	private String detalheSelecionado;

	@NotNull(message = "Site deve ser selecionado.")
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

	// Recupera o Usu�rio logado na Sess�o...
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

	public Periodo[] getPeriodos() {
		return Periodo.values();
	}

	public DataModelTabulacoes getDataModel() {
		return dataModel;
	}

	public void limpa() {
		this.tabulacao = new Tabulacao();
		this.produtoSelecionado = 0;
		this.motivoSelecionado = null;
		this.subMotivoSelecionado = null;
		this.detalheSelecionado = null;
		this.siteSelecionado = 0;
	}
	
	public void tabula() {
		Produto produto = this.produtoDao.buscaPorId(this.produtoSelecionado);

		Arvore arvore = this.arvoreDao.buscaPorAvoreCompleta(produto,
				this.motivoSelecionado, this.subMotivoSelecionado,
				this.detalheSelecionado);

		// RECUPERA O USU�RIO DA SESS�O...
		Usuario usuario = this.usuarioLogado.getUsuario();

		Site site = this.siteDao.buscaPorId(this.siteSelecionado);

		// INSERE OS RELACIONAMENTOS...
		this.insereRelacionamentos(produto, arvore, usuario, site);

		this.tabulacaoDao.adiciona(tabulacao);
		
		this.limpa();
		
		this.addMessage("Info:", "Chamada tabulada com sucesso!");
		
	}

	private void addMessage(String sumario, String detalhe) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				sumario, detalhe);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String redireciona() {
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
