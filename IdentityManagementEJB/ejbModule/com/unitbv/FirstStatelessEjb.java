package com.unitbv;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Identity;
/**
 * Session Bean implementation class FirstStatelessEjb
 */
@Stateless
@LocalBean
public class FirstStatelessEjb implements FirstStatelessEjbRemote{
	
	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public FirstStatelessEjb() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insert(String email, String firstname, String lastname, String password, String username) {
		try
		{
			entityManager.persist(new Identity(email, firstname, lastname, password, username));
			System.out.println("insertion successful!");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
