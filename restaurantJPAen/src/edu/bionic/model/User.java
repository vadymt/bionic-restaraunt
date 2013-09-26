package edu.bionic.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Client
 * 
 */
@NamedQueries({ @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u where u.login = :userlogin") })
@Entity
@Table(name="users" )
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsers")
	private int id;
	
	@Column(name = "login", unique=true, nullable=false)
	private String login;
	@Column(name = "password", nullable=false)
	private String password;
	@ManyToOne
	@JoinColumn(name = "roles_idRole", nullable=false)
	private Role role;
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private Client client;


//	public User(String login, String password, Role role, Client client) {
//		super();
//		this.login = login;
//		this.password = password;
//		this.role = role;
//		this.client = client;
//	}
//
//	public User(String login, String password, Role role) {
//		super();
//		this.login = login;
//		this.password = password;
//		this.role = role;
//	}

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
