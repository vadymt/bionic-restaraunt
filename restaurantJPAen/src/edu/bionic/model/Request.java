package edu.bionic.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Request
 * 
 */
@Entity
@Table(name = "requests")
@NamedQueries({
		@NamedQuery(name = "Request.findAllByClient", query = "SELECT r FROM Request r WHERE r.client.id = :clientid ORDER BY r.id DESC, r.status ASC"),
		@NamedQuery(name = "Request.findAllNotPaid", query = "SELECT r FROM Request r WHERE r.status <> :paidStatus and " +
				"CONCAT(r.client.surname, ' ',r.client.name ) LIKE :like ORDER BY r.status ASC"),
		@NamedQuery(name = "Request.getCount", query = "SELECT COUNT(r) FROM Request r WHERE r.status <> :paidStatus") })
public class Request implements Serializable, DirtyHackForPaging<Integer> {

	private static final long serialVersionUID = 1L;
	public static final int SUBMITTED = 0;
	public static final int IN_KITCHEN = 1;
	public static final int WAITING_TO_BE_CHEQUED = 2;
	public static final int CHEQUED = 3;
	public static final int PAID = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRequest")
	private int id;
	/**
	 * 0 - submitted 1 - kitchen 2 - waiting for cheque 3 - chequed 4 - paid
	 */
	@Column(name = "status", nullable = true)
	private int status = 0;

	@ManyToOne
	@JoinColumn(name = "Client_idClient", nullable = false)
	private Client client;

	@OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Menu_Request> menu_request = new ArrayList<Menu_Request>();

	@OneToOne(mappedBy = "request", fetch = FetchType.LAZY)
	private Tables table;

	@Column(name = "price", nullable = false)
	private BigDecimal price = new BigDecimal("0.0");

	public void calculatePrice() {
		price = new BigDecimal("0.0");
		BigDecimal bd;
		for (Menu_Request mr : menu_request) {
			bd = new BigDecimal(mr.getCount()
					* mr.getMenu().getCost()
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue());
			price = new BigDecimal(price.add(
					bd.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
		}
	}

	public void addMenu_Request(Menu_Request menuRequest) {
		menuRequest.setRequest(this);
		menu_request.add(menuRequest);
	}

	public void removeMenu_Request(int id) {
		for (Menu_Request mr : menu_request) {
			if (mr.getMenu().getId().equals(id)) {
				menu_request.remove(mr);
				return;
			}
		}
	}

	public Menu_Request getMenu_RequestByMenu(Menu menu) {
		for (Menu_Request mr : menu_request) {
			if (mr.getMenu().getId().equals(menu.getId())) {
				return mr;
			}
		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Menu_Request> getMenu_request() {
		return menu_request;
	}

	public void setMenu_request(List<Menu_Request> menu_request) {
		this.menu_request = menu_request;
	}

	public Tables getTable() {
		return table;
	}

	public void setTable(Tables table) {
		this.table = table;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
