package com.unitbv.dao;

import javax.ejb.Remote;

import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.OrganizationDTO;

@Remote
public interface OrganizationDAORemote extends GenericDAO<OrganizationDTO> {
	
	void assignIdentityToOrganization(ModifyAccountDTO modifyAccountDTO, OrganizationDTO organizationDTO);
	
	OrganizationDTO findByName(String name);

}
