package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the resources database table.
 * 
 */
@Entity
@Table(name="resources")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resourceId;

	private String resourceName;

	//bi-directional many-to-one association to Authtype
	@OneToMany(mappedBy="resource")
	private List<Authtype> authtypes;

	//bi-directional many-to-one association to Identityroleresource
	@OneToMany(mappedBy="resource", fetch=FetchType.EAGER)
	private List<Identityroleresources> identityroleresources;

	public Resource() {
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public List<Authtype> getAuthtypes() {
		return this.authtypes;
	}

	public void setAuthtypes(List<Authtype> authtypes) {
		this.authtypes = authtypes;
	}

	public Authtype addAuthtype(Authtype authtype) {
		getAuthtypes().add(authtype);
		authtype.setResource(this);

		return authtype;
	}

	public Authtype removeAuthtype(Authtype authtype) {
		getAuthtypes().remove(authtype);
		authtype.setResource(null);

		return authtype;
	}

	public List<Identityroleresources> getIdentityroleresources() {
		return this.identityroleresources;
	}

	public void setIdentityroleresources(List<Identityroleresources> identityroleresources) {
		this.identityroleresources = identityroleresources;
	}

	public Identityroleresources addIdentityroleresource(Identityroleresources identityroleresource) {
		getIdentityroleresources().add(identityroleresource);
		identityroleresource.setResource(this);

		return identityroleresource;
	}

	public Identityroleresources removeIdentityroleresource(Identityroleresources identityroleresource) {
		getIdentityroleresources().remove(identityroleresource);
		identityroleresource.setResource(null);

		return identityroleresource;
	}

}