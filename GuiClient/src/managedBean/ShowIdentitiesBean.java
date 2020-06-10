package managedBean;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unitbv.dao.IdentityDAORemote;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.ShowIdentityDTO;
import com.unitbv.exception.ModifyAccountException;

@ManagedBean
@SessionScoped
public class ShowIdentitiesBean {
	
	static final Logger LOGGER = Logger.getLogger(ShowIdentitiesBean.class.getName());
	
	@EJB
	IdentityDAORemote identityDAORemote;
	
	ArrayList<ModifyAccountDTO> showIdentityDTOs = new ArrayList<>();
	
	ModifyAccountDTO selectedIdentity;
	
	public ModifyAccountDTO getSelectedIdentity() {
		return selectedIdentity;
	}

	public void setSelectedIdentity(ModifyAccountDTO selectedIdentity) {
		this.selectedIdentity = selectedIdentity;
	}

	public ArrayList<ModifyAccountDTO> getShowIdentityDTOs() {
		return showIdentityDTOs;
	}

	public void setShowIdentityDTOs(ArrayList<ModifyAccountDTO> showIdentityDTOs) {
		this.showIdentityDTOs = showIdentityDTOs;
	}

	public void showIdentities()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try
		{
			showIdentityDTOs = identityDAORemote.showIdentities();
			
			facesContext.getExternalContext().getSessionMap().put("showIdentityDTOs", showIdentityDTOs);
		} 
		catch (ModifyAccountException e)
		{
			facesContext.addMessage("modifyAccountForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
		}
	}

	public String modify(ModifyAccountDTO dto)
	{
		selectedIdentity = dto;
		
		return "/adminFilter/modifyIdentity.xhtml?faces-redirect=true";
	}

}
