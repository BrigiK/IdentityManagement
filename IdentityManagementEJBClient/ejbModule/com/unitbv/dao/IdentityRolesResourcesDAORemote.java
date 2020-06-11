package com.unitbv.dao;

import java.util.List;

import javax.ejb.Remote;

import com.unitbv.dto.IdentityRolesResourcesDTO;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.ResourceDTO;
import com.unitbv.dto.RoleDTO;

@Remote
public interface IdentityRolesResourcesDAORemote extends GenericDAO<IdentityRolesResourcesDTO> {

	public List<IdentityRolesResourcesDTO> findAllForIdentity(int identityId);
	
	public void assignIdentityToRoleResource(ModifyAccountDTO identityDTO, RoleDTO roleDTO, ResourceDTO resourceDTO);
}
