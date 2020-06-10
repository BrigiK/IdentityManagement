package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unitbv.dao.OrganizationDAORemote;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.OrganizationDTO;
import com.unitbv.exception.ModifyAccountException;

@ManagedBean
@SessionScoped
public class EditOrganizationBean {
	
	OrganizationDTO organizationDTO = new OrganizationDTO();
	
	List<OrganizationDTO> organizations  = new ArrayList<>();
	
	String selectedOrganization;

	@EJB
	OrganizationDAORemote organizationDAORemote;

	public String getSelectedOrganization() {
		return selectedOrganization;
	}

	public void setSelectedOrganization(String selectedOrganization) {
		this.selectedOrganization = selectedOrganization;
	}

	public OrganizationDTO getOrganizationDTO() {
		return organizationDTO;
	}

	public void setOrganizationDTO(OrganizationDTO organizationDTO) {
		this.organizationDTO = organizationDTO;
	}
	
	public List<OrganizationDTO> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<OrganizationDTO> organizations) {
		this.organizations = organizations;
	}

	public void showOrganizations()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try
		{
			organizations = organizationDAORemote.findAll();
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" + organizations);
			
			facesContext.getExternalContext().getSessionMap().put("organizations", organizations);
		} 
		catch (ModifyAccountException e)
		{
			facesContext.addMessage("selectExistingOrganizationForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
		}
	}
	
	public void addToOrganization(ModifyAccountDTO modifyAccountDTO, String organization)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try
		{
			OrganizationDTO dto = organizationDAORemote.findByName(organization);
			organizationDAORemote.assignIdentityToOrganization(modifyAccountDTO, dto);
		}
		catch (ModifyAccountException e)
		{
			facesContext.addMessage("selectExistingOrganizationForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
		}
		
		facesContext.addMessage("selectExistingOrganizationForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Organization successfully assigned to identity!", null));
	}
}
