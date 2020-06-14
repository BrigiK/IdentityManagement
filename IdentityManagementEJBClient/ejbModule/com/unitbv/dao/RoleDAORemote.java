package com.unitbv.dao;

import javax.ejb.Remote;

import com.unitbv.dto.RightDTO;
import com.unitbv.dto.RoleDTO;

@Remote
public interface RoleDAORemote extends GenericDAO<RoleDTO> {
	
	RoleDTO getRoleByName(String roleName);

	void assignRightToRole(RoleDTO roleDTO, RightDTO rightDTO);
}
