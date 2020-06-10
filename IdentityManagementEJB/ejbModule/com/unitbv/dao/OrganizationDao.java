package com.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.OrganizationDTO;
import com.unitbv.util.DtoToEntity;
import com.unitbv.util.EntityToDTO;

import model.Identity;
import model.Organization;

@Stateless
@LocalBean
public class OrganizationDao implements OrganizationDAORemote {
	
	@PersistenceContext
	private EntityManager entityManager;

	public OrganizationDao() {
		super();
	}
	
	private EntityToDTO entityToDTO = new EntityToDTO();

	private DtoToEntity dtoToEntity = new DtoToEntity();

	@Override
	public OrganizationDTO findById(int id) {
		Organization organization = entityManager.find(Organization.class, id);
		OrganizationDTO organizationDTO = entityToDTO.convertOrganization(organization);
		
		return organizationDTO;
	}

	@Override
	public List<OrganizationDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM Organization u");
		
		@SuppressWarnings("unchecked")
		List<Organization> organizations = query.getResultList();
		
		List<OrganizationDTO> dtoOrganizations = new ArrayList<>();
		
		for (Organization organization : organizations)
		{
			dtoOrganizations.add(entityToDTO.convertOrganization(organization));
		}
		
		return dtoOrganizations;
	}

	@Override
	public OrganizationDTO create(OrganizationDTO organizationDTO) {
		Organization organization = dtoToEntity.convertOrganization(organizationDTO);
		entityManager.persist(organization);
		entityManager.flush();
		
		return organizationDTO;
	}

	@Override
	public OrganizationDTO update(OrganizationDTO organizationDTO) {
		Organization organization = dtoToEntity.convertOrganization(organizationDTO);
		organization = entityManager.merge(organization);
		
		return organizationDTO;
	}

	@Override
	public void delete(int id) {
		Organization organization = entityManager.find(Organization.class, id);
		entityManager.remove(organization);
	}

	@Override
	public void assignIdentityToOrganization(ModifyAccountDTO modifyAccountDTO, OrganizationDTO organizationDTO) {
		
		Identity identity = entityManager.createNamedQuery("findIdentityByUsername", Identity.class)
				.setParameter("username", modifyAccountDTO.getFirstName().toLowerCase() + "." + modifyAccountDTO.getLastName().toLowerCase()).getSingleResult();
		
		Organization organization = dtoToEntity.convertOrganization(organizationDTO);
		
		System.out.println("IM HERE!!!!!!!" + organization.toString());
		
		identity.setOrganization(organization);
		
		entityManager.merge(identity);
	}

	@Override
	public OrganizationDTO findByName(String name) {
		
		Organization organization = entityManager.createNamedQuery("findOrganizationByName", Organization.class)
				.setParameter("organizationName", name).getSingleResult();
		OrganizationDTO organizationDTO = entityToDTO.convertOrganization(organization);
		
		return organizationDTO;
	}

}
