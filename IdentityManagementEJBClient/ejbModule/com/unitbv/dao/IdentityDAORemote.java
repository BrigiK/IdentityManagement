package com.unitbv.dao;

import javax.ejb.Remote;

import com.unitbv.dto.ChangePasswordDTO;
import com.unitbv.dto.CreateAccountDTO;
import com.unitbv.dto.LoginDTO;
import com.unitbv.dto.IdentityDTO;
import com.unitbv.exception.ChangePasswordException;
import com.unitbv.exception.CreateAccountException;
import com.unitbv.exception.LoginException;

@Remote
public interface IdentityDAORemote extends GenericDAO<IdentityDTO> {

	IdentityDTO loginIdentity(LoginDTO loginDTO) throws LoginException;
	
	CreateAccountDTO createIdentity(CreateAccountDTO createAccountDTO) throws CreateAccountException;

	Boolean updatePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException;
}