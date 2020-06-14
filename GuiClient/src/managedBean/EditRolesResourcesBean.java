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
import com.unitbv.dao.ResourceDAORemote;
import com.unitbv.dao.RoleDAORemote;
import com.unitbv.dto.IdentityRolesResourcesDTO;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.ResourceDTO;
import com.unitbv.dto.RoleDTO;
import com.unitbv.exception.CreateAccountException;

@ManagedBean
@SessionScoped
public class EditRolesResourcesBean {
	
	IdentityRolesResourcesDTO identityRolesResourcesDTO = new IdentityRolesResourcesDTO();
	
	List<IdentityRolesResourcesDTO> dtos = new ArrayList<>();
	
	List<RoleDTO> roles = new ArrayList<>();
	
	List<ResourceDTO> resources = new ArrayList<>();
	
	RoleDTO roleDto = new RoleDTO();
	
	ResourceDTO resourceDTO = new ResourceDTO();
	
	String selectedRole;
	String selectedResource;
	
	@EJB
	IdentityRolesResourcesDAORemote daoRemote;
	
	@EJB
	IdentityDAORemote identityRemote;
	
	@EJB
	RoleDAORemote roleDAORemote;
	
	@EJB
	ResourceDAORemote resourceDAORemote;

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	public String getSelectedResource() {
		return selectedResource;
	}

	public void setSelectedResource(String selectedResource) {
		this.selectedResource = selectedResource;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public List<ResourceDTO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceDTO> resources) {
		this.resources = resources;
	}

	public ResourceDTO getResourceDTO() {
		return resourceDTO;
	}

	public void setResourceDTO(ResourceDTO resourceDTO) {
		this.resourceDTO = resourceDTO;
	}

	public RoleDTO getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDTO roleDto) {
		this.roleDto = roleDto;
	}

	public IdentityRolesResourcesDTO getIdentityRolesResourcesDTO() {
		return identityRolesResourcesDTO;
	}

	public void setIdentityRolesResourcesDTO(IdentityRolesResourcesDTO identityRolesResourcesDTO) {
		this.identityRolesResourcesDTO = identityRolesResourcesDTO;
	}

	public List<IdentityRolesResourcesDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<IdentityRolesResourcesDTO> dtos) {
		this.dtos = dtos;
	}

	public void showRolesResources(ModifyAccountDTO accountDTO)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try
		{
			int id = identityRemote.findByUsername(accountDTO.getUsername()).getId();
			
			dtos = daoRemote.findAllForIdentity(id);
			
			facesContext.getExternalContext().getSessionMap().put("rolesResources", dtos);
		} 
		catch (EJBException e)
		{
			facesContext.addMessage("showRolesResources", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	public void addNewRole()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			roleDto = roleDAORemote.create(roleDto);
			facesContext.getExternalContext().getSessionMap().put("roleDto", roleDto);
			facesContext.addMessage("newRoleForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "New role successfully created!", null));
		} 
		catch (EJBException e) 
		{
			facesContext.addMessage("newRoleForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
		finally {
			roleDto.setName(null);
			roleDto.setDescription(null);
		}
	}
	
	public void addNewResource()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			resourceDTO = resourceDAORemote.create(resourceDTO);
			facesContext.getExternalContext().getSessionMap().put("resourceDTO", resourceDTO);
			facesContext.addMessage("newResourceForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "New resource successfully created!", null));
		} 
		catch (EJBException e) 
		{
			facesContext.addMessage("newResourceForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
		finally {
			resourceDTO.setName(null);
		}
	}
	
	public void showAllRoles()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try
		{
			roles = roleDAORemote.findAll();
			
			facesContext.getExternalContext().getSessionMap().put("roles", roles);
		} 
		catch (EJBException e)
		{
			facesContext.addMessage("allRolesAndResources", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	public void showAllResources()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try
		{
			resources = resourceDAORemote.findAll();
			
			facesContext.getExternalContext().getSessionMap().put("resources", resources);
		} 
		catch (EJBException e)
		{
			facesContext.addMessage("allRolesAndResources", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	public void addRoleResourceToIdentity(ModifyAccountDTO accountDTO)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			RoleDTO role = roleDAORemote.getRoleByName(selectedRole);
			ResourceDTO resource = resourceDAORemote.getResourceByName(selectedResource);
			daoRemote.assignIdentityToRoleResource(accountDTO, role, resource);
			facesContext.addMessage("newResourceForm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected role for selected resource successfully assigned to identity!", null));
		} 
		catch (EJBException e) 
		{
			facesContext.addMessage("newResourceForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), null));
		}
	}
	
	public void removeResourceFromIdentity(ModifyAccountDTO accountDTO, IdentityRolesResourcesDTO dto)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try 
		{
			daoRemote.delete(dto.getIdentityId(), dto.getResourceId(), dto.getRoleId());
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!" + dto.getIdentityUsername());
		}
		catch (EJBException e) {
			facesContext.addMessage("showRolesResources", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
}
