package managedBean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unitbv.dao.IdentityDAORemote;
import com.unitbv.dto.LoginDTO;
import com.unitbv.dto.IdentityDTO;
import com.unitbv.exception.LoginException;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	static final Logger LOGGER = Logger.getLogger(ShowIdentitiesBean.class.getName());

	LoginDTO loginDTO = new LoginDTO();

	@EJB
	IdentityDAORemote identityDAORemote;

	IdentityDTO identityDTO;

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public IdentityDTO getIdentityDTO() {
		return identityDTO;
	}

	public void setIdentityDTO(IdentityDTO identityDTO) {
		this.identityDTO = identityDTO;
	}

	public String loginIdentity() 
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			identityDTO = identityDAORemote.loginIdentity(loginDTO);
			facesContext.getExternalContext().getSessionMap().put("identityDTO", identityDTO);
			// if identityDTO is admin
			System.out.println("admin logged");
			
			return "/adminFilter/admin.xhtml?faces-redirect=true";
		} 
		catch (LoginException e) 
		{
			System.out.println("Invalid username or password");
			facesContext.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			
			return null;
		}
	}

	public String logout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		identityDTO = null;

		return "/index?faces-redirect=true";
	}
}