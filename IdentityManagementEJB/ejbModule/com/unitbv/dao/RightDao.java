package com.unitbv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.unitbv.dto.ResourceDTO;
import com.unitbv.dto.RightDTO;
import com.unitbv.dto.RoleDTO;
import com.unitbv.util.DtoToEntity;
import com.unitbv.util.EntityToDTO;

import model.Resource;
import model.Right;
import model.Role;

@Stateless
@LocalBean
public class RightDao implements RightDAORemote {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private EntityToDTO entityToDTO = new EntityToDTO();

	private DtoToEntity dtoToEntity = new DtoToEntity();

	public RightDao() {
		super();
	}

	@Override
	public RightDTO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RightDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM Right u");
		
		@SuppressWarnings("unchecked")
		List<Right> rights = query.getResultList();
		
		List<RightDTO> dtoRights = new ArrayList<>();
		
		for (Right right : rights) 
		{
			dtoRights.add(entityToDTO.convertRight(right));
		}
		
		return dtoRights;
	}

	@Override
	public RightDTO create(RightDTO rightDTO) {
		Right right = dtoToEntity.convertRight(rightDTO);
		entityManager.persist(right);
		entityManager.flush();
		
		return rightDTO;
	}

	@Override
	public RightDTO update(RightDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RightDTO getRightByName(String rightName) {
		Right right = entityManager.createNamedQuery("findRightByRightName", Right.class)
				.setParameter("rightName", rightName).getSingleResult();
		
		RightDTO dto = entityToDTO.convertRight(right);
		
		return dto;
	}

}
