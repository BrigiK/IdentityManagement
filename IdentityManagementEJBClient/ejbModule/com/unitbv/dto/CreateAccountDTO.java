package com.unitbv.dto;

public class CreateAccountDTO {
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String username;

	public CreateAccountDTO() {
		super();
	}

	public CreateAccountDTO(String firstname, String lastname, String email, String password, String username) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "CreateAccountDTO [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", username=" + username + "]";
	}

}
