package com.unitbv.dto;

import java.util.ArrayList;

public class ModifyAccountDTO {
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String organisationName;
	private ArrayList<String> roles;
	private ArrayList<String> resources;

	public ModifyAccountDTO() {
		super();
	}
	
	public ModifyAccountDTO(String firstName, String lastName, String username, String email, String organisationName,
			ArrayList<String> roles, ArrayList<String> resources) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.organisationName = organisationName;
		this.roles = roles;
		this.resources = resources;
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

	public ArrayList<String> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}

	public ArrayList<String> getResources() {
		return resources;
	}

	public void setResources(ArrayList<String> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "ModifyAccountDTO [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", organisationName=" + organisationName + ", roles=" + roles + ", resources="
				+ resources + "]";
	}
}
