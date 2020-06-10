package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the identity database table.
 * 
 */
@Entity
@NamedQuery(name="Identity.findAll", query="SELECT i FROM Identity i")
@NamedQuery(name = "findIdentityByUsername", query = "SELECT i FROM Identity i WHERE i.username = :username")
public class Identity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identityId;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	private String username;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="organisationId")
	private Organization organization;

	//bi-directional many-to-one association to Identityroleresource
	@OneToMany(mappedBy="identity", fetch=FetchType.EAGER)
	private List<Identityroleresources> identityroleresources;

	public Identity() {
	}
	
	public Identity(String email, String firstname, String lastname) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Identity(String email, String firstname, String lastname, String password, String username) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.username = username;
	}

	public Identity(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getIdentityId() {
		return this.identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Identityroleresources> getIdentityroleresources() {
		return this.identityroleresources;
	}

	public void setIdentityroleresources(List<Identityroleresources> identityroleresources) {
		this.identityroleresources = identityroleresources;
	}

	public Identityroleresources addIdentityroleresource(Identityroleresources identityroleresource) {
		getIdentityroleresources().add(identityroleresource);
		identityroleresource.setIdentity(this);

		return identityroleresource;
	}

	public Identityroleresources removeIdentityroleresource(Identityroleresources identityroleresource) {
		getIdentityroleresources().remove(identityroleresource);
		identityroleresource.setIdentity(null);

		return identityroleresource;
	}

}