package com.unitbv.dto;

public class IdentityRolesResourcesDTO {

	private int identityId;
	private String identityUsername;
	private int resourceId;
	private String resourceName;
	private int roleId;
	private String roleName;
	
	public IdentityRolesResourcesDTO() {
		super();
	}

	public IdentityRolesResourcesDTO(int identityId, String identityUsername, int resourceId, String resourceName,
			int roleId, String roleName) {
		super();
		this.identityId = identityId;
		this.identityUsername = identityUsername;
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.roleId = roleId;
		this.roleName = roleName;
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

	@Override
	public String toString() {
		return "IdentityRolesResourcesDTO [identityId=" + identityId + ", identityUsername=" + identityUsername
				+ ", resourceId=" + resourceId + ", resourceName=" + resourceName + ", roleId=" + roleId + ", roleName="
				+ roleName + "]";
	}
}
