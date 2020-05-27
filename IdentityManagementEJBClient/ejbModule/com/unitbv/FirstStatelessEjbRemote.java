package com.unitbv;

import javax.ejb.Remote;

@Remote
public interface FirstStatelessEjbRemote {
	
	void insert(String email, String firstname, String lastname, String password, String username);
}
