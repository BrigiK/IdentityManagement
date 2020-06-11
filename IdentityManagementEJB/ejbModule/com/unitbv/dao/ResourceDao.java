package com.unitbv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.ResourceDTO;
import com.unitbv.dto.RoleDTO;
import com.unitbv.util.DtoToEntity;
import com.unitbv.util.EntityToDTO;

import model.Identity;
import model.Resource;
import model.Role;

@Stateless
@LocalBean
public class ResourceDao implements ResourceDAORemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	public ResourceDao() {
	}
	
	private EntityToDTO entityToDTO = new EntityToDTO();

	private DtoToEntity dtoToEntity = new DtoToEntity();

	@Override
	public ResourceDTO findById(int id) {
		Resource resource = entityManager.find(Resource.class, id);
		ResourceDTO dto = entityToDTO.convertResource(resource);
		
		return dto;
	}

	@Override
	public List<ResourceDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM Resource u");
		
		@SuppressWarnings("unchecked")
		List<Resource> resources = query.getResultList();
		
		List<ResourceDTO> dtoResources = new ArrayList<>();
		
		for (Resource resource : resources) 
		{
			dtoResources.add(entityToDTO.convertResource(resource));
		}
		
		return dtoResources;
	}

	@Override
	public ResourceDTO create(ResourceDTO resourceDTO) {
		Resource resource = dtoToEntity.convertResource(resourceDTO);
		entityManager.persist(resource);
		entityManager.flush();
		resourceDTO.setId(resource.getResourceId());
		
		return resourceDTO;
	}

	@Override
	public ResourceDTO update(ResourceDTO resourceDTO) {
		Resource resource = dtoToEntity.convertResource(resourceDTO);
		resource.setResourceId(resourceDTO.getId());
		resource = entityManager.merge(resource);
		
		return resourceDTO;
	}

	@Override
	public void delete(int id) {
		Resource resource = entityManager.find(Resource.class, id);
		entityManager.remove(resource);
	}

	@Override
	public ResourceDTO getResourceByName(String resourceName) {
		Resource resource = entityManager.createNamedQuery("findResourceByResourceName", Resource.class)
				.setParameter("resourceName", resourceName).getSingleResult();
		
		ResourceDTO dto = entityToDTO.convertResource(resource);
		
		return dto;
	}
}
