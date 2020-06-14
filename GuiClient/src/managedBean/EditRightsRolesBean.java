package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unitbv.dao.RightDAORemote;
import com.unitbv.dao.RoleDAORemote;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.ResourceDTO;
import com.unitbv.dto.RightDTO;
import com.unitbv.dto.RoleDTO;

@ManagedBean
@SessionScoped
public class EditRightsRolesBean {
	
	RightDTO rightDTO = new RightDTO();
	
	RoleDTO roleDTO = new RoleDTO();
	
	List<RoleDTO> roleDTOs = new ArrayList<>();
	
	List<RightDTO> rightDTOs = new ArrayList<>();
	
	String selectedRight;
	String selectedRole;
	
	public String getSelectedRight() {
		return selectedRight;
	}

	public void setSelectedRight(String selectedRight) {
		this.selectedRight = selectedRight;
	}

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	public List<RightDTO> getRightDTOs() {
		return rightDTOs;
	}

	public void setRightDTOs(List<RightDTO> rightDTOs) {
		this.rightDTOs = rightDTOs;
	}

	public List<RoleDTO> getRoleDTOs() {
		return roleDTOs;
	}

	public void setRoleDTOs(List<RoleDTO> roleDTOs) {
		this.roleDTOs = roleDTOs;
	}

	public RightDTO getRightDTO() {
		return rightDTO;
	}

	public void setRightDTO(RightDTO rightDTO) {
		this.rightDTO = rightDTO;
	}

	@EJB
	RoleDAORemote roleDAORemote;
	
	@EJB
	RightDAORemote rightDAORemote;

	public void showRolesRights()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try
		{
			roleDTOs = roleDAORemote.findAll();
			
			facesContext.getExternalContext().getSessionMap().put("roleDTOs", roleDTOs);
		} 
		catch (EJBException e)
		{
			facesContext.addMessage("showRolesRights", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	public void addNewRight()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			rightDTO = rightDAORemote.create(rightDTO);
			facesContext.getExternalContext().getSessionMap().put("rightDTO", rightDTO);
			facesContext.addMessage("newRightForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "New right successfully created!", null));
		} 
		catch (EJBException e) 
		{
			facesContext.addMessage("newRightForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
		finally {
			rightDTO.setName(null);
			rightDTO.setDescription(null);
		}
	}
	
	public void showAllRights()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try
		{
			rightDTOs = rightDAORemote.findAll();
			
			facesContext.getExternalContext().getSessionMap().put("rightDTOs", rightDTOs);
		} 
		catch (EJBException e)
		{
			facesContext.addMessage("allRolesAndRights", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	public void addRightToRole()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			RoleDTO role = roleDAORemote.getRoleByName(selectedRole);
			RightDTO right = rightDAORemote.getRightByName(selectedRight);
			roleDAORemote.assignRightToRole(role, right);
			facesContext.addMessage("allRolesAndRights", new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected role for selected resource successfully assigned to identity!", null));
		} 
		catch (EJBException e) 
		{
			facesContext.addMessage("allRolesAndRights", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}
}
