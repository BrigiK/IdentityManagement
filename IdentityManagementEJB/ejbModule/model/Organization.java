package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the organizations database table.
 * 
 */
@Entity
@Table(name="organizations")
@NamedQuery(name="Organization.findAll", query="SELECT o FROM Organization o")
@NamedQuery(name = "findOrganizationByName", query = "SELECT i FROM Organization i WHERE i.organizationName = :organizationName")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int organizationId;

	private String cui;

	private String organizationName;

	//bi-directional many-to-one association to Identity
	@OneToMany(mappedBy="organization", fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Identity> identities;

	public Organization() {
	}

	public Organization(int id, String organizationName, String cui) {
		super();
		this.organizationId = id;
		this.organizationName = organizationName;
		this.cui = cui;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getCui() {
		return this.cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public List<Identity> getIdentities() {
		return this.identities;
	}

	public void setIdentities(List<Identity> identities) {
		this.identities = identities;
	}

	public Identity addIdentity(Identity identity) {
		getIdentities().add(identity);
		identity.setOrganization(this);

		return identity;
	}

	public Identity removeIdentity(Identity identity) {
		getIdentities().remove(identity);
		identity.setOrganization(null);

		return identity;
	}

	@Override
	public String toString() {
		return "Organization [organizationId=" + organizationId + ", cui=" + cui + ", organizationName="
				+ organizationName + ", identities=" + identities + "]";
	}

}