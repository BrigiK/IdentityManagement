package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
@NamedQuery(name = "findRoleByRoleName", query = "SELECT i FROM Role i WHERE i.roleName = :roleName")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;

	private String roleDescription;

	private String roleName;

	//bi-directional many-to-one association to Identityroleresource
	@OneToMany(mappedBy="role", fetch=FetchType.EAGER)
	private List<Identityroleresources> identityroleresources = new ArrayList<Identityroleresources>();

	//bi-directional many-to-many association to Right
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="rightroles"
		, joinColumns={
			@JoinColumn(name="roleId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="rightId")
			}
		)
	private List<Right> rights;

	public Role() {
	}

	public Role(String roleName, String roleDescription) {
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}
	
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Identityroleresources> getIdentityroleresources() {
		return this.identityroleresources;
	}

	public void setIdentityroleresources(List<Identityroleresources> identityroleresources) {
		this.identityroleresources = identityroleresources;
	}

	public Identityroleresources addIdentityroleresource(Identityroleresources identityroleresource) {
		getIdentityroleresources().add(identityroleresource);
		identityroleresource.setRole(this);

		return identityroleresource;
	}

	public Identityroleresources removeIdentityroleresource(Identityroleresources identityroleresource) {
		getIdentityroleresources().remove(identityroleresource);
		identityroleresource.setRole(null);

		return identityroleresource;
	}

	public List<Right> getRights() {
		return this.rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

}