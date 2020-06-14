package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unitbv.dao.IdentityDAORemote;
import com.unitbv.dao.IdentityRolesResourcesDAORemote;
import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.IdentityRolesRightsResourcesDTO;
import com.unitbv.dto.LoginDTO;
import com.unitbv.exception.LoginException;

@ManagedBean
@SessionScoped
public class SkypeBean {
	
	LoginDTO loginDTO = new LoginDTO();
	
	@EJB
	IdentityRolesResourcesDAORemote irrRemote;
	
	@EJB
	IdentityDAORemote identityDAORemote;
	
	IdentityDTO identityDTO;
	
	String newPassword;
	
	List<IdentityRolesRightsResourcesDTO> dtos = new ArrayList<>();
	
	public List<IdentityRolesRightsResourcesDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<IdentityRolesRightsResourcesDTO> dtos) {
		this.dtos = dtos;
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
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
			
			if(identityDAORemote.hasSkypeAccount(identityDTO))
			{
				return "/userFilter/skypeHome.xhtml?faces-redirect=true";
			}
			else
			{
				facesContext.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must have access to Skype in order to access this page!", null));
				return null;
			}
		} 
		catch (LoginException e)
		{
			System.out.println("Invalid username or password");
			facesContext.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			
			return null;
		}
	}
	
	public void changePassword()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			identityDAORemote.changePassword(identityDTO, newPassword);
			facesContext.getExternalContext().getSessionMap().put("identityDTO", identityDTO);
			
			facesContext.addMessage("changePasswordForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Password successfully changed!", null));
		} 
		catch (EJBException e)
		{
			facesContext.addMessage("changePasswordForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	public void showRolesRights()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			dtos = irrRemote.findRolesRightsForIdentity(identityDTO.getId());
		} 
		catch (EJBException e)
		{
			facesContext.addMessage("changePasswordForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}

}
