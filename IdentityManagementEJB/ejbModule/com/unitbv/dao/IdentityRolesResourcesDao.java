package com.unitbv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.IdentityRolesResourcesDTO;
import com.unitbv.dto.IdentityRolesRightsResourcesDTO;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.ResourceDTO;
import com.unitbv.dto.RoleDTO;
import com.unitbv.util.DtoToEntity;
import com.unitbv.util.EntityToDTO;

import model.Identity;
import model.Identityroleresources;
import model.Resource;
import model.Role;

@Stateless
@LocalBean
public class IdentityRolesResourcesDao implements IdentityRolesResourcesDAORemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	public IdentityRolesResourcesDao() {
	}
	
	private EntityToDTO entityToDTO = new EntityToDTO();

	private DtoToEntity dtoToEntity = new DtoToEntity();
	
	@Override
	public IdentityRolesResourcesDTO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdentityRolesResourcesDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM Identityroleresources u");
		
		@SuppressWarnings("unchecked")
		List<Identityroleresources> identityroleresources = query.getResultList();
		
		System.out.println(identityroleresources.toString());
		
		List<IdentityRolesResourcesDTO> dtoIdRoRes = new ArrayList<>();
		
		for (Identityroleresources idRoRes : identityroleresources) 
		{
			dtoIdRoRes.add(entityToDTO.convertIdRoRe(idRoRes));
		}
		
		return dtoIdRoRes;
	}

	@Override
	public IdentityRolesResourcesDTO create(IdentityRolesResourcesDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdentityRolesResourcesDTO update(IdentityRolesResourcesDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<IdentityRolesResourcesDTO> findAllForIdentity(int identityId) {
		
		Query query = entityManager.createQuery("SELECT u FROM Identityroleresources u WHERE u.identity.identityId = :identityId").setParameter("identityId", identityId);
		
		@SuppressWarnings("unchecked")
		List<Identityroleresources> identityroleresources = query.getResultList();
		
		System.out.println(identityroleresources.toString());
		
		List<IdentityRolesResourcesDTO> dtoIdRoRes = new ArrayList<>();
		
		for (Identityroleresources idRoRes : identityroleresources) 
		{
			dtoIdRoRes.add(entityToDTO.convertIdRoRe(idRoRes));
		}
		
		return dtoIdRoRes;
	}

	@Override
	public void assignIdentityToRoleResource(ModifyAccountDTO identityDTO, RoleDTO roleDTO, ResourceDTO resourceDTO) {
	
		Identity identity = entityManager.createNamedQuery("findIdentityByUsername", Identity.class)
				.setParameter("username", identityDTO.getUsername()).getSingleResult();
		
		//Role role = dtoToEntity.convertRole(roleDTO);
		Role role = entityManager.createNamedQuery("findRoleByRoleName", Role.class)
				.setParameter("roleName", roleDTO.getName()).getSingleResult();
		
		Resource resource = dtoToEntity.convertResource(resourceDTO);
		
		Identityroleresources identityroleresources = new Identityroleresources(identity, resource, role);
		
		role.getIdentityroleresources().add(identityroleresources);
		resource.getIdentityroleresources().add(identityroleresources);
		identity.getIdentityroleresources().add(identityroleresources);
		entityManager.persist(identityroleresources);
		entityManager.flush();
	}

	@Override
	public void delete(int identityId, int resourceId, int roleId) {
		
		Query selectQuery = entityManager.createQuery("SELECT u FROM Identityroleresources u WHERE u.identity.identityId = :identityId AND u.resource.resourceId = :resourceId AND u.role.roleId = :roleId");
		selectQuery.setParameter("identityId", identityId);
		selectQuery.setParameter("resourceId", resourceId);
		selectQuery.setParameter("roleId", roleId);
		
		Identityroleresources identityroleresources = (Identityroleresources) selectQuery.getSingleResult();
		
		Query deleteQuery = entityManager.createQuery("DELETE FROM Identityroleresources u WHERE u.identity.identityId = :identityId AND u.resource.resourceId = :resourceId AND u.role.roleId = :roleId");
		deleteQuery.setParameter("identityId", identityId);
		deleteQuery.setParameter("resourceId", resourceId);
		deleteQuery.setParameter("roleId", roleId);
		deleteQuery.executeUpdate();
		
		Identity identity = entityManager.find(Identity.class, identityId);
		Role role = entityManager.find(Role.class, roleId);
		Resource resource = entityManager.find(Resource.class, resourceId);
		
		identity.getIdentityroleresources().remove(identityroleresources);
		role.getIdentityroleresources().remove(identityroleresources);
		resource.getIdentityroleresources().remove(identityroleresources);
	}
	
	@Override
	public List<IdentityRolesRightsResourcesDTO> findRolesRightsForIdentity(int identityId) {
		
		Query query = entityManager.createQuery("SELECT u FROM Identityroleresources u WHERE u.identity.identityId = :identityId").setParameter("identityId", identityId);
		
		@SuppressWarnings("unchecked")
		List<Identityroleresources> identityroleresources = query.getResultList();
		
		List<IdentityRolesRightsResourcesDTO> dtoIdRoRiRes = new ArrayList<>();
		
		for (Identityroleresources idRoRes : identityroleresources)
		{
			dtoIdRoRiRes.add(entityToDTO.convertIdRoRiRe(idRoRes));
		}
		
		return dtoIdRoRiRes;
	}

}
