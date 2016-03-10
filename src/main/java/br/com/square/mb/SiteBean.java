package br.com.square.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.square.dao.SiteDao;
import br.com.square.modelo.Site;

@Named
@RequestScoped
public class SiteBean {
	private Site site = new Site();

	private List<Site> sites;
	
	private List<Site> sitesAtivos;

	@Inject
	private SiteDao siteDao;

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<Site> getSites() {
		if (this.sites == null) {
			this.siteDao.lista();
		}
		return sites;
	}
	
	public List<Site> getSitesAtivos() {
		if(this.sitesAtivos == null) {
			this.sitesAtivos = siteDao.listaAtivos();
		}
		return sitesAtivos;
	}

	public void salva() {
		if (this.site.getId() == 0L) {
			this.siteDao.adiciona(site);
		} else {
			this.siteDao.atualiza(site);
		}

		this.limpa();
		this.listaTodos();
	}

	private void listaTodos() {
		this.sites = this.siteDao.lista();
	}

	private void limpa() {
		this.site = new Site();
	}
}
