package com.unitbv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.unitbv.dao.IdentityDAORemote;
import com.unitbv.dto.ChangePasswordDTO;
import com.unitbv.dto.CreateAccountDTO;
import com.unitbv.dto.LoginDTO;
import com.unitbv.dto.IdentityDTO;
import com.unitbv.exception.ChangePasswordException;
import com.unitbv.exception.CreateAccountException;
import com.unitbv.exception.LoginException;
import com.unitbv.util.DtoToEntity;
import com.unitbv.util.EntityToDTO;

import model.Identity;;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless
@LocalBean
public class IdentityDao implements IdentityDAORemote {

	static final Logger LOGGER = Logger.getLogger(IdentityDao.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	public IdentityDao() {
	}

	private EntityToDTO entityToDTO = new EntityToDTO();

	private DtoToEntity dtoToEntity = new DtoToEntity();

	@Override
	public IdentityDTO findById(int id) 
	{
		Identity identity = entityManager.find(Identity.class, id);
		IdentityDTO identityDTO = entityToDTO.convertIdentity(identity);
		
		return identityDTO;
	}

	@Override
	public List<IdentityDTO> findAll() 
	{
		Query query = entityManager.createQuery("SELECT u FROM User u");
		
		@SuppressWarnings("unchecked")
		List<Identity> identities = query.getResultList();
		
		System.out.println(identities.toString());
		
		List<IdentityDTO> dtoIdentities = new ArrayList<>();
		
		for (Identity identity : identities) 
		{
			dtoIdentities.add(entityToDTO.convertIdentity(identity));
		}
		
		return dtoIdentities;
	}

	@Override
	public IdentityDTO create(IdentityDTO identityDTO)
	{
		Identity identity = dtoToEntity.convertIdentity(identityDTO);
		entityManager.persist(identity);
		entityManager.flush();
		identityDTO.setId(identity.getIdentityId());
		
		return identityDTO;
	}

	@Override
	public IdentityDTO update(IdentityDTO identityDTO) 
	{
		Identity identity = dtoToEntity.convertIdentity(identityDTO);
		identity.setIdentityId(identityDTO.getId());
		identity = entityManager.merge(identity);
		
		return identityDTO;
	}

	@Override
	public void delete(int id) 
	{
		Identity identity = entityManager.find(Identity.class, id);
		entityManager.remove(identity);
	}

	@Override
	public IdentityDTO loginIdentity(LoginDTO loginDTO) throws LoginException 
	{
		Identity identity = null;
		
		try 
		{
			identity = entityManager.createNamedQuery("findIdentityByUsername", Identity.class)
					.setParameter("username", loginDTO.getUsername()).getSingleResult();
		} 
		catch (NoResultException e) 
		{
			throw new LoginException("Wrong authentication! Incorrect username!");
		}
		
		if (!loginDTO.getPassword().equals(identity.getPassword())) 
		{
			throw new LoginException("Wrong authentication! Incorrect password!");
		}

		IdentityDTO identityDTO = entityToDTO.convertIdentity(identity);
		
		return identityDTO;
	}

	@Override
	public Boolean updatePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException 
	{
		Identity identity = null;
		LOGGER.log(Level.INFO, "Trying to update password for:  " + changePasswordDTO.toString());
		
		try 
		{
			identity = entityManager.createNamedQuery("findIdentityByUsername", Identity.class)
					.setParameter("username", changePasswordDTO.getUsername()).getSingleResult();
			
			if (identity.getPassword().equals(changePasswordDTO.getOldPassword())) 
			{
				if (!changePasswordDTO.getOldPassword().equals(changePasswordDTO.getNewPassword())) 
				{
					identity.setPassword(changePasswordDTO.getNewPassword());
					identity = entityManager.merge(identity);
					LOGGER.log(Level.INFO, "Successfully changed password for:  " + changePasswordDTO.toString());
					
					return true;
				} 
				else 
				{
					throw new ChangePasswordException(
							"Please choose another new password, not the same as the old one!");
				}
			} 
			else
				throw new ChangePasswordException("The old password is not valid.");
		} 
		catch (NoResultException e) 
		{
			throw new ChangePasswordException("The username is not valid!");
		}
	}

	@Override
	public CreateAccountDTO createIdentity(CreateAccountDTO createAccountDTO) throws CreateAccountException 
	{
		Identity identity = dtoToEntity.convertCAIdentity(createAccountDTO);
		
		List<Identity> identitites = entityManager.createNamedQuery("findIdentityByUsername", Identity.class)
				.setParameter("username", identity.getUsername()).getResultList();
		LOGGER.log(Level.INFO, String.valueOf(identitites.size()));
		
		if(identitites.size() > 0)
		{
			throw new CreateAccountException("Username already in use!");
		}
		
		if(identity.getEmail() == null || identity.getFirstname() == null || identity.getLastname() == null || identity.getPassword() == null)
		{
			throw new CreateAccountException("All fields are required!");
		}
		
		entityManager.persist(identity);
		entityManager.flush();
		
		return createAccountDTO;
	}
}
