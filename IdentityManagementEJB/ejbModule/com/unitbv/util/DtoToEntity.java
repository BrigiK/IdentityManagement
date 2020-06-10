package com.unitbv.util;

import com.unitbv.dto.CreateAccountDTO;
import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.OrganizationDTO;

import model.Identity;
import model.Organization;

public class DtoToEntity {

	// all classes doesn't take primary key in account

	public Identity convertIdentity(IdentityDTO identityDTO) 
	{
		Identity identity = new Identity(identityDTO.getUsername(), identityDTO.getPassword());

		return identity;
	}
	
	public Organization convertOrganization(OrganizationDTO dto)
	{
		Organization organization = new Organization(dto.getId() , dto.getName(), dto.getCui());
		
		return organization;
	}
	
	public Identity convertCAIdentity(CreateAccountDTO createAccountDTO)
	{
		Identity identity = new Identity(createAccountDTO.getEmail(), createAccountDTO.getFirstname(), createAccountDTO.getLastname());

		return identity;
	}

}
