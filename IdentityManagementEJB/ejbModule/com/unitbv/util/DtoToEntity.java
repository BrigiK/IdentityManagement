package com.unitbv.util;

import com.unitbv.dto.CreateAccountDTO;
import com.unitbv.dto.IdentityDTO;

import model.Identity;

public class DtoToEntity {

	// all classes doesn't take primary key in account

	public Identity convertIdentity(IdentityDTO identityDTO) 
	{
		Identity identity = new Identity(identityDTO.getUsername(), identityDTO.getPassword());

		return identity;
	}
	
	public Identity convertCAIdentity(CreateAccountDTO createAccountDTO)
	{
		Identity identity = new Identity(createAccountDTO.getEmail(), createAccountDTO.getFirstname(), createAccountDTO.getLastname(), createAccountDTO.getPassword(), createAccountDTO.getUsername());

		return identity;
	}

}
