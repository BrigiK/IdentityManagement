package managedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unitbv.dao.IdentityDAORemote;
import com.unitbv.dto.CreateAccountDTO;
import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.LoginDTO;
import com.unitbv.exception.CreateAccountException;
import com.unitbv.exception.LoginException;

@ManagedBean
@SessionScoped
public class CreateAccountBean {

	CreateAccountDTO createAccountDTO = new CreateAccountDTO();
	
	@EJB
	IdentityDAORemote identityDAORemote;
	
	IdentityDTO identityDTO;
	
	public CreateAccountDTO getCreateAccountDTO() {
		return createAccountDTO;
	}

	public void setCreateAccountDTO(CreateAccountDTO createAccountDTO) {
		this.createAccountDTO = createAccountDTO;
	}

	public IdentityDTO getIdentityDTO() {
		return identityDTO;
	}

	public void setIdentityDTO(IdentityDTO identityDTO) {
		this.identityDTO = identityDTO;
	}
	
	public String createIdentityAccount()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			createAccountDTO = identityDAORemote.createIdentity(createAccountDTO);
			facesContext.getExternalContext().getSessionMap().put("createAccountDTO", createAccountDTO);
			// if identityDTO is admin
			System.out.println("Account successfully created!");
			
			return "/adminFilter/accountCreatedSuccessfully.xhtml?faces-redirect=true";
		} 
		catch (CreateAccountException e) 
		{
			System.out.println("Couldn't create account!");
			facesContext.addMessage("createAccountForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			
			return null;
		}
	}
}
