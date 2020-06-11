package com.unitbv.dao;

import javax.ejb.Remote;

import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.RoleDTO;

@Remote
public interface RoleDAORemote extends GenericDAO<RoleDTO> {
	
	void assignIdentityToRole(ModifyAccountDTO modifyAccountDTO, RoleDTO roleDTO);
	
	RoleDTO getRoleByName(String roleName);

}
