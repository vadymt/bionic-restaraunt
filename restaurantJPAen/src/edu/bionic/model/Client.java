package edu.bionic.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Client
 * 
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idClient")
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "surname", nullable = false)
	private String surname;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private Set<Request> requests = new HashSet<Request>();

	@OneToOne
	@JoinColumn(name = "Users_idUsers", nullable = false)
	private User user;

	private static final long serialVersionUID = 1L;

	public Client() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> albums) {
		this.requests = albums;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addRequest(Request request) {
		requests.add(request);
	}

}
