package com.unitbv.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.unitbv.dto.CreateAccountDTO;
import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.IdentityRolesResourcesDTO;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.OrganizationDTO;
import com.unitbv.dto.ResourceDTO;
import com.unitbv.dto.RoleDTO;

import model.Identity;
import model.Identityroleresources;
import model.Organization;
import model.Resource;
import model.Role;

public class DtoToEntity {

	// all classes doesn't take primary key in account

	public Identity convertIdentity(IdentityDTO identityDTO) 
	{
		Identity identity = new Identity(identityDTO.getUsername(), identityDTO.getPassword());

		return identity;
	}
	
	public Organization convertOrganization(OrganizationDTO dto)
	{
		int dtoId = 0;
		try
		{
			dtoId = dto.getId();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		if(dtoId == 0)
		{
			dtoId = ThreadLocalRandom.current().nextInt(10, 151);
		}
		
		Organization organization = new Organization(dtoId , dto.getName(), dto.getCui());
		return organization;
	}
	
	public Role convertRole(RoleDTO dto)
	{
		Role role = new Role(dto.getName(), dto.getDescription());
		
		return role;
	}
	
	public Resource convertResource(ResourceDTO dto)
	{
		Resource resource = new Resource(dto.getId(), dto.getName());
		
		return resource;
	}
	
	public Identityroleresources convertIdRoRe(Identity identity, Resource resource, Role role)
	{
		Identityroleresources identityroleresources = new Identityroleresources(identity, resource, role);
		
		return identityroleresources;
	}
	
	public Identity convertCAIdentity(CreateAccountDTO createAccountDTO)
	{
		Identity identity = new Identity(createAccountDTO.getEmail(), createAccountDTO.getFirstname(), createAccountDTO.getLastname());

		return identity;
	}

}
