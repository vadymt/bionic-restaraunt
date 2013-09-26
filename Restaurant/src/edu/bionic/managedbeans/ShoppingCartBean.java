package edu.bionic.managedbeans;

//<!--  <a4j:commandButton ajaxSingle="true" id="addaproduct"
//
//value="Add" oncomplete="#{shoppingCartBean.addProduct()}">
//<a4j:ajax event="onclick" >
//	<a4j:param name="setMenu1" assignTo="#{shoppingCartBean.id}"
//		value="#{item.id}" />
//	<a4j:param name="setcurrentRow1"
//		assignTo="#{shoppingCartBean.currentrow}" value="#{row}" />
//</a4j:ajax>
//
//</a4j:commandButton>-->

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import edu.bionic.dao.MenuDao;
import edu.bionic.dao.RequestDao;
import edu.bionic.model.Client;
import edu.bionic.model.Menu;
import edu.bionic.model.Menu_Request;
import edu.bionic.model.Request;

@ManagedBean(name = "shoppingCartBean")
@SessionScoped()
public class ShoppingCartBean {
	@ManagedProperty(value = "#{securityUtilities}")
	private SecurityUtilities securityUtilities;
	@EJB
	private RequestDao requestDao;
	@EJB
	private MenuDao menuDao;
	// @EJB
	// private UserDao userDao;
	private Integer id = null;
	private Request request;
	private Menu menu;
//	private Integer currentrow = 3;
//	private Integer[] count;
	/*number of rows to be shown in table*/
	private Integer numberOfRows = 10;
//	private Set<Integer> keyToUpdate;
	private int currentcount = 1;

	public ShoppingCartBean() {

	}

	// @PostConstruct
	private void setClient() {
		request = new Request();
		Client client;
		client = securityUtilities.getUser().getClient();
		request.setClient(client);
	}

	public String addProduct() {
		// Menu menu = menuDao.read(menuId);
		Menu_Request menuRequest;
		if (request == null) {
			setClient();
		}
		if ((menuRequest = request.getMenu_RequestByMenu(menu)) != null) {
			menuRequest.setCount(menuRequest.getCount() + currentcount);
		} else {
			menuRequest = new Menu_Request();
			menuRequest.setCount(currentcount);
			menuRequest.setMenu(menu);
			request.addMenu_Request(menuRequest);
		}
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, currentcount
				+ " pieces of " + menu.getName()
				+ " has been seccusfully added to your request!", null));
		currentcount = 1;
		request.calculatePrice();
		return "success";
	}

	public void currentCountListener(ValueChangeEvent e) {
		String s = e.getNewValue().toString();
		currentcount = Integer.parseInt(s);
	}

	public void submitRequest() {
		request.calculatePrice();
		requestDao.create(request);
		request = null;
		// return "main.xhtml?faces-redirect=true";
	}

	public void clearRequest() {
		request = null;
		// return "main.xhtml?faces-redirect=true";
	}

	public void removeProduct() {
		if(id==null) {
			return;
		}
		request.removeMenu_Request(id);
		request.calculatePrice();
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public SecurityUtilities getSecurityUtilities() {
		return securityUtilities;
	}

	public void setSecurityUtilities(SecurityUtilities securityUtilities) {
		this.securityUtilities = securityUtilities;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

//	public Integer[] getCount() {
//		return count;
//	}
//
//	public void setCount(Integer[] count) {
//		this.count = count;
//	}

//	public Integer getCurrentrow() {
//		return currentrow;
//	}
//
//	public void setCurrentrow(Integer currentrow) {
//		this.currentrow = currentrow;
//	}

	public Integer getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

//	public Set<Integer> getKeyToUpdate() {
//		return keyToUpdate;
//	}
//
//	public void setKeyToUpdate(Set<Integer> keyToUpdate) {
//		this.keyToUpdate = keyToUpdate;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCurrentcount() {
		return currentcount;
	}

	public void setCurrentcount(int currentcount) {
		this.currentcount = currentcount;
	}

}
