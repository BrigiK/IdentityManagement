package com.unitbv.dto;

import java.util.List;

public class RoleDTO {
	
	private String name;
	private String description;
	private List<RightDTO> rights;
	
	public RoleDTO() {
		super();
	}

	public RoleDTO(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public RoleDTO(String name, String description, List<RightDTO> rights) {
		super();
		this.name = name;
		this.description = description;
		this.rights = rights;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RightDTO> getRights() {
		return rights;
	}

	public void setRights(List<RightDTO> rights) {
		this.rights = rights;
	}

	@Override
	public String toString() {
		return name;
	}

}
