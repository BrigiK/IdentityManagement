package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the identityroleresources database table.
 * 
 */
@Entity
@Table(name="identityroleresources")
@NamedQuery(name="Identityroleresources.findAll", query="SELECT i FROM Identityroleresources i")
public class Identityroleresources implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identityroleresourcesId;

	//bi-directional many-to-one association to Identity
	@ManyToOne
	@JoinColumn(name="identityId")
	private Identity identity;

	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="resourceId")
	private Resource resource;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;

	public Identityroleresources() {
	}

	public int getIdentityroleresourcesId() {
		return this.identityroleresourcesId;
	}

	public void setIdentityroleresourcesId(int identityroleresourcesId) {
		this.identityroleresourcesId = identityroleresourcesId;
	}

	public Identity getIdentity() {
		return this.identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}