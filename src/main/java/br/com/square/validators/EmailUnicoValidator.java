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
	
	@Inject
	private ComponentResolver resolver;
	
	
	@Override
	public void validate(FacesContext ctx, UIComponent component, Object value)
			throws ValidatorException {
		
		String id = resolver.getSubmittedValue("idUsuario");
		String email = String.valueOf(value);
		String jpql = "SELECT COUNT(u) FROM Usuario u WHERE u.email LIKE :pEmail";
		
		System.out.println("id usuario --> " + id );
		System.out.println("Email--> " + email );
		
		Query query = this.manager.createQuery(jpql);
		query.setParameter("pEmail", email);
		Long count =  (Long) query.getSingleResult();
		
		if((count != 0) && (id.equals("0"))) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,"Usu�rio j� cadastrado.", null));
		}
	}

}
