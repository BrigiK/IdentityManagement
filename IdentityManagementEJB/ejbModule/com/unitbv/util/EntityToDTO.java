package com.unitbv.util;

import java.util.ArrayList;
import java.util.List;

import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.OrganizationDTO;

import model.Identity;
import model.Identityroleresources;
import model.Organization;

public class EntityToDTO {

	public IdentityDTO convertIdentity(Identity identity) 
	{
		IdentityDTO globalIdentityDTO = new IdentityDTO(identity.getUsername(), identity.getPassword());
		globalIdentityDTO.setId(identity.getIdentityId());
		
		return globalIdentityDTO;
	}
	
	public OrganizationDTO convertOrganization(Organization organization)
	{
		OrganizationDTO organizationDTO = new OrganizationDTO(organization.getOrganizationId(), organization.getOrganizationName(), organization.getCui());
		
		return organizationDTO;
	}
	
	public ModifyAccountDTO identityToShow(Identity identity)
	{
		String org = null;
		List<Identityroleresources> idRolRes = new ArrayList<>();
		ArrayList<String> roles = new ArrayList<>();
		ArrayList<String> resources = new ArrayList<>();
		
		identity.getIdentityroleresources().isEmpty();
		idRolRes = identity.getIdentityroleresources();
		
		try
		{
			org = identity.getOrganization().getOrganizationName();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		if(org == null)
		{
			org = "None";
		}
		
		for(Identityroleresources roleRes : idRolRes)
		{
			if(!roles.contains(roleRes.getRole().getRoleName()))
			{
				roles.add(roleRes.getRole().getRoleName());
			}
			
			if(!resources.contains(roleRes.getResource().getResourceName()))
			{
				resources.add(roleRes.getResource().getResourceName());
			}
		}
		
		ModifyAccountDTO dto = new ModifyAccountDTO(identity.getFirstname(), identity.getLastname(), identity.getUsername(), identity.getEmail(), org, roles, resources);
		
		return dto;
	}

}
