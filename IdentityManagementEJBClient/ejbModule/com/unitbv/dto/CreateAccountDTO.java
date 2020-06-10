package com.unitbv.dto;

public class CreateAccountDTO {
	
	private String firstname;
	private String lastname;
	private String email;

	public CreateAccountDTO() {
		super();
	}

	public CreateAccountDTO(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "CreateAccountDTO [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ "]";
	}

}
