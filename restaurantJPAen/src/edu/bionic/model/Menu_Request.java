package edu.bionic.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MenuPosition_has_Request
 *
 */
@Entity
@Table(name="menuposition_has_request")
public class Menu_Request implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Menu_Request() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMENU_REQUEST")
	private Integer id;
	@Column(name = "count", nullable=false)
	private int count;
	@ManyToOne
	@JoinColumn(name = "idMenuPosition", nullable=false)
	private Menu menu;
	@ManyToOne
	@JoinColumn(name = "idRequest", nullable=false)
	private Request request;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	

   
}
