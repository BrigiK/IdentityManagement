package com.unitbv.util;

import com.unitbv.dto.IdentityDTO;

import model.Identity;

public class EntityToDTO {

	public IdentityDTO convertIdentity(Identity identity) 
	{
		IdentityDTO globalIdentityDTO = new IdentityDTO(identity.getUsername(), identity.getPassword());
		globalIdentityDTO.setId(identity.getIdentityId());
		
		return globalIdentityDTO;
	}

}
