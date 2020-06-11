package com.unitbv.dao;

import javax.ejb.Remote;

import com.unitbv.dto.ResourceDTO;

@Remote
public interface ResourceDAORemote extends GenericDAO<ResourceDTO> {

	ResourceDTO getResourceByName(String resourceName);
}
