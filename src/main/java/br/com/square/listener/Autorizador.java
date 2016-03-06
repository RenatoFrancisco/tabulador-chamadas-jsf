package br.com.square.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.square.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener {

	private static final String TELA_LOGIN = "login?faces-redirect=true";

	@Inject
	private UsuarioLogadoBean usuarioLogado;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();

		if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}

		// Verifica��o se o usu�rio est� logado...
		if (!usuarioLogado.isLogado()) {
			NavigationHandler handler = context.getApplication()
					.getNavigationHandler();

			handler.handleNavigation(context, null, TELA_LOGIN);

			// Renderiza a tela...
			context.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
