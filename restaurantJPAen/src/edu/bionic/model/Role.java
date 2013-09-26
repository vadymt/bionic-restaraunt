package edu.bionic.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Role
 * 
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idroles")
	private Integer id;
	@Column(name = "rolename", nullable=false, unique=true)
	private String rolename;

	public Role() {
		super();
	}
	
	public Role(Integer id, String rolename) {
		super();
		this.id = id;
		this.rolename = rolename;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
