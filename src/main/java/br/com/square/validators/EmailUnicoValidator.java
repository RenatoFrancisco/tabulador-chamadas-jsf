package br.com.square.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@FacesValidator("emailUnico")
public class EmailUnicoValidator implements Validator{

	@Inject
	private EntityManager manager;
	
	@Override
	public void validate(FacesContext ctx, UIComponent component, Object value)
			throws ValidatorException {
		
		String email = String.valueOf(value);
		String jpql = "SELECT COUNT(u) FROM Usuario u WHERE u.email LIKE :pEmail";
		
		Query query = this.manager.createQuery(jpql);
		query.setParameter("pEmail", email);
		Long count =  (Long) query.getSingleResult();
		
		if(count != 0) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,"Usuário já cadastrado.", null));
		}
	}

}
