package com.unitbv.dto;

import java.util.List;

public class IdentityRolesRightsResourcesDTO {

	private int identityId;
	private String identityUsername;
	private int resourceId;
	private String resourceName;
	private int roleId;
	private String roleName;
	private List<RightDTO> rights;
	
	public IdentityRolesRightsResourcesDTO() {
		super();
	}

	public IdentityRolesRightsResourcesDTO(int identityId, String identityUsername, int resourceId, String resourceName,
			int roleId, String roleName, List<RightDTO> rights) {
		super();
		this.identityId = identityId;
		this.identityUsername = identityUsername;
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.roleId = roleId;
		this.roleName = roleName;
		this.rights = rights;
	}

	public int getIdentityId() {
		return identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public String getIdentityUsername() {
		return identityUsername;
	}

	public void setIdentityUsername(String identityUsername) {
		this.identityUsername = identityUsername;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RightDTO> getRights() {
		return rights;
	}

	public void setRights(List<RightDTO> rights) {
		this.rights = rights;
	}

	@Override
	public String toString() {
		return "IdentityRolesRightsResourcesDTO [identityId=" + identityId + ", identityUsername=" + identityUsername
				+ ", resourceId=" + resourceId + ", resourceName=" + resourceName + ", roleId=" + roleId + ", roleName="
				+ roleName + ", rights=" + rights + "]";
	}
	
}
