package edu.bionic.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tables
 *
 */
@Entity
@Table(name="tables")
public class Tables implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Tables() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTables")
	private Integer id;
	
	@Column(name = "seats", nullable=false)
	private int seats;
	
//	@Column(name = "Request_idRequest")
//	private int Request_idRequest;

	@OneToOne
	@JoinColumn(name = "idRequest")
	private Request request ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

//	public int getRequest_idRequest() {
//		return Request_idRequest;
//	}
//
//	public void setRequest_idRequest(int request_idRequest) {
//		Request_idRequest = request_idRequest;
//	}


	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

   
}
