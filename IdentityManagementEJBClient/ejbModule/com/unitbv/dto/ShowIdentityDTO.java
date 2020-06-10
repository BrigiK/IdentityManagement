package com.unitbv.dto;

import java.io.Serializable;

public class ShowIdentityDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String organisationName;
	
	public ShowIdentityDTO() {
		super();
	}

	public ShowIdentityDTO(String firstName, String lastName, String username, String email,
			String organisationName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.organisationName = organisationName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	@Override
	public String toString() {
		return "ShowIdentityDTO [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", organisationName=" + organisationName + "]";
	}
}
