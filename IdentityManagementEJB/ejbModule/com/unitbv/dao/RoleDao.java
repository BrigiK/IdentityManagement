package com.unitbv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.unitbv.dto.IdentityDTO;
import com.unitbv.dto.ModifyAccountDTO;
import com.unitbv.dto.RoleDTO;
import com.unitbv.util.DtoToEntity;
import com.unitbv.util.EntityToDTO;

import model.Identity;
import model.Organization;
import model.Role;

@Stateless
@LocalBean
public class RoleDao implements RoleDAORemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	public RoleDao() {
	}
	
	private EntityToDTO entityToDTO = new EntityToDTO();

	private DtoToEntity dtoToEntity = new DtoToEntity();
	
	@Override
	public RoleDTO findById(int id) {
		Role role = entityManager.find(Role.class, id);
		RoleDTO roleDTO = entityToDTO.convertRole(role);
		
		return roleDTO;
	}

	@Override
	public List<RoleDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM Role u");
		
		@SuppressWarnings("unchecked")
		List<Role> roles = query.getResultList();
		
		System.out.println(roles.toString());
		
		List<RoleDTO> dtoRoles = new ArrayList<>();
		
		for (Role role : roles) 
		{
			dtoRoles.add(entityToDTO.convertRole(role));
		}
		
		return dtoRoles;
	}

	@Override
	public RoleDTO create(RoleDTO roleDTO) {
		Role role = dtoToEntity.convertRole(roleDTO);
		entityManager.persist(role);
		entityManager.flush();
		
		return roleDTO;
	}

	@Override
	public RoleDTO update(RoleDTO roleDTO) {
		Role role = dtoToEntity.convertRole(roleDTO);
		role = entityManager.merge(role);
		
		return roleDTO;
	}

	@Override
	public void delete(int id) {
		Role role = entityManager.find(Role.class, id);
		entityManager.remove(role);
	}

	@Override
	public void assignIdentityToRole(ModifyAccountDTO modifyAccountDTO, RoleDTO roleDTO) {
		
//		Identity identity = entityManager.createNamedQuery("findIdentityByUsername", Identity.class)
//				.setParameter("username", modifyAccountDTO.getFirstName().toLowerCase() + "." + modifyAccountDTO.getLastName().toLowerCase()).getSingleResult();
//		
//		Role role = dtoToEntity.convertRole(roleDTO);
//		
//		System.out.println("IM HERE!!!!!!!" + role.toString());
//		
//		identity.set(organization);
//		
//		entityManager.merge(identity);
	}

	@Override
	public RoleDTO getRoleByName(String roleName) {
		
		Role role = entityManager.createNamedQuery("findRoleByRoleName", Role.class)
				.setParameter("roleName", roleName).getSingleResult();
		
		RoleDTO dto = entityToDTO.convertRole(role);
		
		return dto;
	}

}
